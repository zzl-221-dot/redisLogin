//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lsjc.beta.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;

public class ObjectUtil {
    public ObjectUtil() {
    }

    public static <T> boolean isEmpty(T t) {
        if (t == null) {
            return true;
        } else if (!(t instanceof String)) {
            if (t instanceof Map) {
                return ((Map)t).isEmpty();
            } else {
                return t instanceof Collection ? ((Collection)t).isEmpty() : false;
            }
        } else {
            return "".equals(t.toString()) || "null".equalsIgnoreCase(t.toString());
        }
    }

    public static List<JSONObject> getJSONObjectList(String billStr) {
        List<JSONObject> bills = new ArrayList();
        Object billObj = JSON.parse(billStr);
        if (billObj instanceof JSONObject) {
            bills.add((JSONObject)billObj);
        }

        if (billObj instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray)billObj;

            for(int i = 0; i < jsonArray.size(); ++i) {
                bills.add(jsonArray.getJSONObject(i));
            }
        }

        return bills;
    }

    public static JSONArray getJsonArray(String billStr) {
        JSONArray bills = new JSONArray();
        Object billObj = JSON.parse(billStr);
        if (billObj instanceof JSONObject) {
            bills.add(billObj);
        }

        if (billObj instanceof JSONArray) {
            bills = (JSONArray)billObj;
        }

        return bills;
    }

    public static JSONObject getJsonObject(String billStr) {
        Object billObj = JSON.parse(billStr);
        if (billObj instanceof JSONObject) {
            return (JSONObject)billObj;
        } else {
            return billObj instanceof JSONArray ? ((JSONArray)billObj).getJSONObject(0) : new JSONObject();
        }
    }

    public static String getJsonString(Object data) {
        String billStr;
        if (data instanceof String) {
            billStr = data.toString();
        } else {
            billStr = JSON.toJSONStringWithDateFormat(data, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat});
        }

        return billStr;
    }

    public static <T> boolean isNotEmpty(T t) {
        return !isEmpty(t);
    }

    public static <T> boolean isAnyEmpty(T... tArray) {
        Object[] var1 = tArray;
        int var2 = tArray.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            T t = (T) var1[var3];
            if (isEmpty(t)) {
                return true;
            }
        }

        return false;
    }

    public static String getStringValue(Object o) {
        if (null == o) {
            return null;
        } else {
            String value = String.valueOf(o);
            return "null".equalsIgnoreCase(value) ? "" : value;
        }
    }

    public static String getString(Object o, String... keys) {
        String value = null;
        String[] var3 = keys;
        int var4 = keys.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String key = var3[var5];
            value = getString(o, key);
            if (isNotEmpty(value)) {
                break;
            }
        }

        return value;
    }

    public static String getString(Object o, String key) {
        if (isAnyEmpty(o, key)) {
            return null;
        } else if (o instanceof Map) {
            Object value = ((Map)o).get(key);
            return getStringValue(value);
        } else {
            return null;
        }
    }

    public static List<String> getList(Object o, String key) {
        List<String> result = new ArrayList();
        if (isAnyEmpty(o, key)) {
            return result;
        } else {
            if (o instanceof Map) {
                Object value = ((Map)o).get(key);
                if (value instanceof List) {
                    List valueList = (List)((List)value);
                    Iterator var5 = valueList.iterator();

                    while(var5.hasNext()) {
                        Object obj = var5.next();

                        try {
                            result.add(obj.toString());
                        } catch (Exception var8) {
                        }
                    }
                }
            }

            return result;
        }
    }

    public static int getInt(Object o, String key) {
        if (isAnyEmpty(o, key)) {
            return 0;
        } else if (o instanceof Map) {
            Object value = ((Map)o).get(key);
            String s = getStringValue(value);
            if (isEmpty(s)) {
                return 0;
            } else {
                try {
                    return Integer.parseInt(s);
                } catch (NumberFormatException var5) {
                    return 0;
                }
            }
        } else {
            return 0;
        }
    }

    public static boolean getBoolean(Object o, String key) {
        if (isAnyEmpty(o, key)) {
            return false;
        } else if (o instanceof Map) {
            Object value = ((Map)o).get(key);
            String s = getStringValue(value);
            if (isEmpty(s)) {
                return false;
            } else {
                try {
                    return !"0".equalsIgnoreCase(s);
                } catch (NumberFormatException var5) {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public static Set<String> splitStringByComma(String str) {
        Set<String> set = new HashSet();
        if (isNotEmpty(str)) {
            String[] ids = str.split(",");
            String[] var3 = ids;
            int var4 = ids.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String id = var3[var5];
                set.add(id);
            }
        }

        return set;
    }
}
