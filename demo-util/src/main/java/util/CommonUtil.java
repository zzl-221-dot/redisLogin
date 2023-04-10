package util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lirb
 * 功能：
 * 日期：2023-4-10-14:29
 * 版本       开发者     描述
 * 1.0.0     lirb     ...
 */
public class CommonUtil {
    private CommonUtil(){}

    public static BigDecimal string2BigDecimal(String value) {
        return string2BigDecimal(value, null);
    }

    public static BigDecimal string2BigDecimal(String value, BigDecimal defaultValue) {
        return value == null || value.isEmpty() ? defaultValue : new BigDecimal(value);
    }

    public static BigDecimal string2BigDecimalAndFormat(String value, BigDecimal defaultValue, int scale, int mode) {
        if (value == null || value.isEmpty()) {
            return defaultValue;
        } else {
            BigDecimal bigDecimal = new BigDecimal(value);
            return bigDecimal.setScale(scale, mode);
        }
    }

    public static String getString(String value, String defaultValue) {
        return value == null || value.isEmpty() ? defaultValue : value;
    }

    public static String getString(Object obj, String defaultStr) {
        return obj == null ? defaultStr : obj.toString();
    }

    /**
     * 校验是否vin码
     * @param vin
     * @return
     */
    public static boolean validVin(String vin) {
        boolean flag = false;
        if (StringUtils.isNotBlank(vin)) {
            flag = vin.length() == 17 && (vin.toUpperCase().startsWith("LMV") || vin.toUpperCase().startsWith("L1N"));
        }
        return flag;
    }

    /**
     * 中国电信号段
     *         133,149,153,173,177,180,181,189,199
     *     中国联通号段
     *         130,131,132,145,155,156,166,175,176,185,186
     *     中国移动号段
     *         134(0-8),135,136,137,138,139,147,150,151,152,157,158,159,178,182,183,184,187,188,198
     *     其他号段
     *         14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
     *         虚拟运营商
     *             电信：1700,1701,1702
     *             移动：1703,1705,1706
     *             联通：1704,1707,1708,1709,171
     *         卫星通信：148(移动) 1349
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String s2="^[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";// 验证手机号
        if(StringUtils.isNotBlank(str)){
            p = Pattern.compile(s2);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }


    /**
     * 格式化为百分比
     * @return
     */
    public static String percentFormat(double number){
        if(Double.isNaN(number)){
            return "0.00%";
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2); // 设置两位小数位
        return nf.format(number);

    }

    /**
     * 格式化为百分比
     * @return
     */
    public static String percentFormat(float number){
        if(Double.isNaN(number)){
            return "0.00%";
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2); // 设置两位小数位
        return nf.format(number);

    }


    public static String formatNumber(String value, int scale) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        BigDecimal bigDecimal = new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.toString();
    }

    public static String formatNumber(BigDecimal value, int scale) {
        if (value == null) {
            return "";
        }

        BigDecimal bigDecimal = value.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.toString();
    }

    public static BigDecimal getBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof Integer) {
            return new BigDecimal((Integer) obj);
        }

        if (obj instanceof Double) {
            return BigDecimal.valueOf((Double) obj);
        }

        if (obj instanceof String) {
            String strObj = (String) obj;
            return strObj.isEmpty() ? null : new BigDecimal(strObj);
        }

        return null;
    }
}
