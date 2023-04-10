package util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author lirb
 * 功能：
 * 日期：2023-4-10-14:43
 * 版本       开发者     描述
 * 1.0.0     lirb     ...
 */
@Slf4j
public class SecretUtil {
    private SecretUtil() {}

    //生成随机数字和字母,
    public static String getRandomStr(int length) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        //length为几位密码
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else {
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }

    public static String getNumberStr(int length) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            val.append(random.nextInt(10));
        }
        return val.toString();
    }
}
