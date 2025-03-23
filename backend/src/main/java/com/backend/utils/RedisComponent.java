package com.backend.utils;

import com.backend.entity.dto.UserSpaceDto;
import com.backend.entity.po.FileInfo;
import com.backend.entity.query.FileInfoQuery;
import com.backend.mappers.FileInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.backend.entity.constant.Constants.*;

@Component
public class RedisComponent  {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private FileInfoMapper<FileInfo, FileInfoQuery> fileInfoMapper;

    public void saveUserSpaceDto(Integer userId, UserSpaceDto userSpaceDto) {
        redisUtil.setex(
                REDIS_KEY_USER_SPACE_USE + userId,
                userSpaceDto,
                REDIS_KEY_EXPIRES_DAY
        );
    }

    public UserSpaceDto getUserSpaceUse(Integer userId) {
        UserSpaceDto spaceDto = (UserSpaceDto) redisUtil.get(REDIS_KEY_USER_SPACE_USE + userId);

        if (spaceDto == null) {
            spaceDto = new UserSpaceDto();
            Long useSpace = fileInfoMapper.selectUseSpace(userId);
            spaceDto.setUseSpace(useSpace);
            spaceDto.setTotalSpace(USER_INIT_TOTAL_SPACE);
            saveUserSpaceDto(userId, spaceDto);
        }

        return spaceDto;
    }

}
