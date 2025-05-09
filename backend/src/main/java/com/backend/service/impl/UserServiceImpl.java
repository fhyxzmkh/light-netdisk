package com.backend.service.impl;

import com.alibaba.fastjson.JSON;
import com.backend.config.security.JwtUtil;
import com.backend.config.security.UserDetailsImpl;
import com.backend.entity.dto.UserInfoDto;
import com.backend.entity.dto.UserSpaceDto;
import com.backend.entity.enums.UserStatusEnums;
import com.backend.entity.po.FileInfo;
import com.backend.entity.po.User;
import com.backend.entity.query.FileInfoQuery;
import com.backend.mappers.FileInfoMapper;
import com.backend.mappers.UserMapper;
import com.backend.service.UserService;
import com.backend.utils.RedisComponent;
import com.backend.utils.UserUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private FileInfoMapper<FileInfo, FileInfoQuery> fileInfoMapper;


    @Override
    public Map<String, String> login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();

        User user = loginUser.getUser();

        // 用户被禁用
        if (UserStatusEnums.DISABLED.getStatus().equals(user.getStatus())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "User is disabled");
            return response;
        }

        // 更新用户最后登录时间
        User updateInfo = new User();
        updateInfo.setLastLoginTime(new Date());
        userMapper.updateById(updateInfo);

        String jwt = JwtUtil.createJWT(user.getUserId().toString());

        Map<String, String> response = new java.util.HashMap<>();
        response.put("message", "success");
        response.put("token", jwt);

        // 用户信息
        UserInfoDto userInfoDto = UserUtil.getUserInfoDto(user);
        response.put("userInfo", JSON.toJSONString(userInfoDto));

        // 用户空间信息
        UserSpaceDto userSpaceDto = new UserSpaceDto();
        Long useSpace = fileInfoMapper.selectUseSpace(user.getUserId());
        userSpaceDto.setUseSpace(useSpace);
        userSpaceDto.setTotalSpace(user.getTotalSpace());

        response.put("userSpaceInfo", JSON.toJSONString(userSpaceDto));
        redisComponent.saveUserSpaceDto(user.getUserId(), userSpaceDto);

        return response;
    }

    @Override
    public Map<String, String> register(String username, String password, String confirmPassword) {
        Map<String, String> response = new HashMap<>();

        if (username == null || username.isEmpty()) {
            response.put("message", "Username is required");
            return response;
        }

        if (password == null || password.isEmpty()) {
            response.put("message", "Password is required");
            return response;
        }

        if (confirmPassword == null || confirmPassword.isEmpty()) {
            response.put("message", "Confirm password is required");
            return response;
        }

        username = username.trim();
        if (username.length() < 3 || username.length() > 30) {
            response.put("message", "Username must be between 3 and 30 characters");
            return response;
        }

        if (password.length() < 3 || password.length() > 60) {
            response.put("message", "Password must be between 3 and 60 characters");
            return response;
        }

        if (!password.equals(confirmPassword)) {
            response.put("message", "Passwords do not match");
            return response;
        }

        // 用户名不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            response.put("message", "Username already exists");
            return response;
        }

        String encodedPassword = passwordEncoder.encode(password);
        String avatar = "https://cdn.acwing.com/media/user/profile/photo/226251_lg_be03581796.jpg";

        User user = new User(
                null,
                username,
                avatar,
                encodedPassword,
                null,
                null,
                null,
                null,
                null,
                null
        );
        userMapper.insert(user);

        response.put("message", "success");
        return response;
    }
}
