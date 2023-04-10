package util;

import constant.LoginConstant;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class Base64Util {
    /***
     * BASE64解密
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryBASE64(String key) {
        try {
            return (new BASE64Decoder()).decodeBuffer(key);
        } catch (IOException e) {
            throw new RuntimeException(LoginConstant.DECRYPTIONFAILURE);
        }
    }

    /***
     * BASE64加密
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) {
        return (new BASE64Encoder()).encode(key);
    }
}
