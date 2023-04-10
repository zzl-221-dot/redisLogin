package util;

import java.util.UUID;

/**
 * @program: demo
 * @description: 生成id
 * @author: zhang zl
 * @created: 2023/04/06 15:49
 */
public class UtilId {
    public static String getUUID() {
        // 随机生成一位整数
        int random = (int) (Math.random() * 9 + 1);
        String valueOf = String.valueOf(random);
        // 生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        // 若为负数 取反
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        String value = valueOf + String.format("%015d", hashCode);

        return value;
    }
}
