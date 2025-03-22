package com.backend.utils;

import cn.hutool.core.bean.BeanUtil;
import com.backend.config.security.UserDetailsImpl;
import com.backend.entity.dto.UserInfoDto;
import com.backend.entity.po.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static User getLoggedUser() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();

        return loginUser.getUser();
    }

    @NotNull
    public static UserInfoDto getUserInfoDto(User userPojo) {
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtil.copyProperties(userPojo, userInfoDto);
        return userInfoDto;
    }

}
