package com.lsjc.beta.enums;

public enum Role {
    SUPERADMINISTRATOR("001","超级管理员"),
    ADMINISTRATORS("002","管理员"),
    DOMESTICCONSUMER("003","普通用户");

    private final String code;
    private final String name;

    Role(String code,String name){
        this.code = code;
        this.name = name;
    }

    public String getCode(){return code;}
    public String getName(){return name;}
}
