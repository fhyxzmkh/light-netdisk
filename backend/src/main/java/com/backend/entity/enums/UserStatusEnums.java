package com.backend.entity.enums;

public enum UserStatusEnums {

    DISABLED(0, "禁用"),
    ENABLED(1, "启用");

    private final Integer status;
    private final String desc;

    UserStatusEnums(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

}
