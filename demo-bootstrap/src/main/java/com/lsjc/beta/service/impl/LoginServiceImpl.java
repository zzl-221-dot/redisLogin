package com.lsjc.beta.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lsjc.beta.constant.LoginConstant;
import com.lsjc.beta.entity.Users;
import com.lsjc.beta.enums.Role;
import com.lsjc.beta.enums.UserDr;
import com.lsjc.beta.mapper.UserMapper;
import com.lsjc.beta.service.ILoginService;
import com.lsjc.beta.util.*;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Log4j
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisUtils redisUtils;


    private ReturnJson returnJson = new ReturnJson();

    private JSONObject jsonObject = new JSONObject();

    /**
     * 用户登录
     *
     * @param users
     * @return
     */
    @Override
    public JSONObject login(Users users) {
        // 打印登录入参
        log.info("login/login:" + JSONObject.toJSON(users));
        // redis 链接是否正常
        Boolean isRedis = redisUtils.checkIsRedis();

        try {
            // 当传入的对象不为 Null时 先检测环境是否拥有 redis环境
            if (ObjectUtil.isNotEmpty(users)) {
                if (StringUtil.isEmpty(users.getUserCardId()) || StringUtil.isEmpty(users.getPassword())) {
                    throw new RuntimeException(LoginConstant.IDORPASSWORDISNULL);
                }
                Users usersLogin = null;
                // 若连接正常 则执行 redis登录 若异常则直连数据库
                if (isRedis) {
                    String password = redisUtil.get(users.getUserCardId());
                    // 若从redis中获取的 value不为空 证明其在24小时内登录过 直接用redis进行密码校验
                    if (StringUtil.isNotEmpty(password)) {
                        if (password.equals(users.getPassword())) {
                            // 若账号密码无误 则刷新redis缓存时间 并登录成功
                            redisUtil.setEx(users.getUserCardId(), users.getPassword(), 24, TimeUnit.HOURS);
                            jsonObject = returnJson.resStatus(true, LoginConstant.LOGINSUCCESS);
                        }

                    } else {
                        // 若redis中不存在登录信息 则从数据库中查询
                        usersLogin = userMapper.selectUserByCardIdAndPass(users);
                        if (ObjectUtil.isNotEmpty(usersLogin)) {
                            // 若账号密码无误 则刷新redis缓存时间 并登录成功
                            redisUtil.setEx(users.getUserCardId(), users.getPassword(), 24, TimeUnit.HOURS);
                            jsonObject = returnJson.resStatus(true, LoginConstant.LOGINSUCCESS);
                        } else {
                            // 若根据账号密码未查询到 则失败
                            jsonObject = returnJson.resStatus(false, LoginConstant.IDORPASSWORDISERR);
                        }
                    }
                } else {
                    // 若未开启redis 则从数据库中查询
                    usersLogin = userMapper.selectUserByCardIdAndPass(users);
                    if (ObjectUtil.isNotEmpty(usersLogin)) {
                        // 若账号密码无误 则登录成功
                        jsonObject = returnJson.resStatus(true, LoginConstant.LOGINSUCCESS);
                    } else {
                        // 若根据账号密码未查询到 则失败
                        jsonObject = returnJson.resStatus(false, LoginConstant.IDORPASSWORDISERR);
                    }
                }
            }
        } catch (RuntimeException e) {
            // 打印登录异常消息
            log.error(LoginConstant.LOGINFAILD + e.getMessage(), e);
            jsonObject = returnJson.resStatus(false, LoginConstant.LOGINFAILD + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public JSONObject registe(Users users) {
        log.error("login/registe:" + JSONObject.toJSON(users));
        // redis 链接是否正常
        Boolean isRedis = redisUtils.checkIsRedis();
        try {
            // 校验传入信息 并配置注册参数
            users = this.regex(users);
            //TODO 插入数据库操作应做缓存

            // 注册成功后插入数据库
            int insert = userMapper.insert(users);
            if (insert > 0) {
                // 插入数据库成功之后 返回用户注册后的账户 把信息存入redis当中
                redisUtil.setEx(users.getUserCardId(), users.getPassword(), 24, TimeUnit.HOURS);
                jsonObject = returnJson.resStatus(true, LoginConstant.REGISTSUCCESS);
                jsonObject.put("UserCardId", users.getUserCardId());
            } else {
                jsonObject = returnJson.resStatus(false, LoginConstant.REGISTFAILDPERSONTOOMAORE);
            }
        } catch (RuntimeException e) {
            // 打印注册异常信息
            log.error(LoginConstant.REGISTERFAILD + e.getMessage(), e);
            jsonObject = returnJson.resStatus(false, LoginConstant.REGISTERFAILD + e.getMessage());
        }

        return jsonObject;
    }

    /**
     * 注册信息校验 并配置参数
     *
     * @param users
     * @return
     */
    private Users regex(Users users) {
        // 先校验是否传入信息异常
        if (ObjectUtil.isEmpty(users)) {
            throw new RuntimeException(LoginConstant.REGISTEDEATAILISNOTNULL);
        }

        // 用户 名称
        if (StringUtil.isEmpty(users.getUserName())) {
            throw new RuntimeException(LoginConstant.REGISTUSERNAMEISNOTNULL);
        }
        // 用户 密码
        if (StringUtil.isEmpty(users.getPassword())) {
            throw new RuntimeException(LoginConstant.REGISTUSERPASSWORDISNOTNULL);
        }
        // 用户 手机号
        String phone = users.getPhone();
        if (StringUtil.isEmpty(phone)) {
            throw new RuntimeException(LoginConstant.REGISTUSERPHONEISNOTNULL);
        } else {
            // 校验输入的手机号的正确性
            String phoneRegex = "(13\\d|14[579]|15[^4\\D]|17[^49\\D]|18\\d)\\d{8}";
            Pattern pattern = Pattern.compile(phoneRegex);    // 编译正则表达式
            Matcher matcher = pattern.matcher(phone);    // 创建给定输入模式的匹配器
            boolean bool = matcher.matches();
            if (!bool) {
                throw new RuntimeException(LoginConstant.REGISTPHONEISNOTTRUE);
            }
        }
        // 用户 邮箱
        String email = users.getEmail();
        if (StringUtil.isEmpty(email)) {
            throw new RuntimeException(LoginConstant.REGISTUSEREMAILISNOTNULL);
        } else {
            // 校验输入的手机号的正确性
            String emailRegex = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
            Pattern pattern = Pattern.compile(emailRegex);    // 编译正则表达式
            Matcher matcher = pattern.matcher(email);    // 创建给定输入模式的匹配器
            boolean bool = matcher.matches();
            if (!bool) {
                throw new RuntimeException(LoginConstant.REGISTEMAILISNOTTRUE);
            }
        }

        // 若未传入角色 则默认为普通用户
        if (StringUtil.isEmpty(users.getRole())) {
            users.setRole(Role.DOMESTICCONSUMER.getCode());
        }
        // 设置一个id --多线程下id可能会重复
        String id = checkUserId(users.getId());
        users.setId(id);

        // 设置一个用户的专属 cardID
        // 手机号后四位为头
        String head = phone.substring(phone.length() - 4);
        // 角色作为体
        String body = users.getRole();
        // 用户邮箱前四位为尾
        String end = users.getEmail().substring(0, 4);
        users.setUserCardId(head + body + end);

        // 设置数据为激活
        users.setDr(UserDr.DRZERO.getCode());

        // 创建时间
        users.setCreateTime(new Date());
        // 时间戳
        users.setPubts(new Date());

        // 查询该用户的手机和邮箱是否被注册过
        int count = userMapper.selectCountByCardId(users.getUserCardId());
        if (count > 0) {
            throw new RuntimeException(LoginConstant.REGISTINGFOUNDMORECARDID);
        }

        return users;
    }

    // 赋值id
    private String checkUserId(String userId) {
        if (StringUtil.isEmpty(userId)) {
            userId = UtilId.getUUID();
        }
        // 若存在此id则重新生成 并重新获取id
        int count = userMapper.selectCountById(userId);
        if (count > 0) {
            userId = UtilId.getUUID();
            this.checkUserId(userId);
        }

        return userId;
    }

}
