package entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * users
 * @author
 */
@Data
public class Users implements Serializable {
    /**
     * id

     */
    private String id;

    /**
     * 用户
     */
    private String userName;

    /**
     * 用户身份卡id
     */
    private String userCardId;

    /**
     * 统一密码
     */
    private String password;

    /**
     * 标识
     */
    private String dr;

    /**
     * 角色
     */
    private String role;

    /**
     * 级别
     */
    private String level;

    /**
     * 创建人
     */
    private String controlUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间

     */
    private Date pubts;

    /**
     * 昵称备用2
     */
    private String name2;

    /**
     * 昵称备用3
     */
    private String name3;

    /**
     * 密码备用2
     */
    private String password2;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    private static final long serialVersionUID = 1L;
}