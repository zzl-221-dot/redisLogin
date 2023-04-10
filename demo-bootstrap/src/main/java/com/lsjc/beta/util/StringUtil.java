//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lsjc.beta.util;

public class StringUtil {
    public StringUtil() {
    }

    public static boolean isEmpty(String str) {
        return null == str || str.trim().equals("");
    }

    public static boolean isNotEmpty(String str) {
        return null != str && !str.trim().equals("");
    }
}
