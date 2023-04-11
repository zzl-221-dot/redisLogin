package constant;

/**
 * @program: demo
 * @description: 登录常量
 * @author: zhang zl
 * @created: 2023/04/04 16:59
 */
public class LoginConstant {
    public static final String LOGINFAILD = "登录失败：";
    public static final String IDORPASSWORDISNULL = "输入的用户名或密码不能为空";
    public static final String LOGINSUCCESS = "登录成功";
    public static final String IDORPASSWORDISERR = LOGINFAILD + "账号或密码错误";

    public static final Object REGISTERFAILD = "注册失败：";
    public static final String REGISTEDEATAILISNOTNULL = "您尚未填写任何注册内容";
    public static final String REGISTUSERNAMEISNOTNULL = "用户名称不能为空";
    public static final String REGISTUSERPASSWORDISNOTNULL = "一级密码不能为空";
    public static final String REGISTUSERPHONEISNOTNULL = "手机号不能为空";
    public static final String REGISTUSEREMAILISNOTNULL = "邮箱不能为空";
    public static final String REGISTPHONEISNOTTRUE = "输入的手机号码不正确";
    public static final String REGISTEMAILISNOTTRUE = "输入的邮箱不正确";
    public static final String REGISTSUCCESS = "注册成功";
    public static final String REGISTFAILDPERSONTOOMAORE = "哎呀，网络波动了一下呢，请重新试试。";
    public static final String REGISTINGFOUNDMORECARDID = "手机号或邮箱已经被注册过啦";
    public static final String ENCRYPTIONFAILURE = "加密失败";
    //默认密码
    public static final String DEFAULTPASSWORD = "qwe123";
    public static final String DECRYPTIONFAILURE = "解密失败";
}
