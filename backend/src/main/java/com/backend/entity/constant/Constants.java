package com.backend.entity.constant;

public class Constants {

    // 普通用户
    public static final int USER_TYPE_USER = 0;

    // 管理员
    public static final int USER_TYPE_ADMIN = 1;

    // 5mb
    public static final long USER_INIT_TOTAL_SPACE = 5242880;


    public static final Integer REDIS_KEY_EXPIRES_DAY = 60 * 60 * 24;


    public static final String REDIS_KEY_USER_SPACE_USE = "user:spaceuse:";

}
