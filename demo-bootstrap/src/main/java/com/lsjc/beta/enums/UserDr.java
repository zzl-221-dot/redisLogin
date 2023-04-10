package com.lsjc.beta.enums;

public enum UserDr {
    DRZERO("0", "激活"),
    DRONE("1", "未激活"),
    DRTWO("2", "已锁定");

    private final String code;
    private final String name;

    UserDr(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public java.lang.String getCode() {
        return code;
    }

    public java.lang.String getName() {
        return name;
    }
}
