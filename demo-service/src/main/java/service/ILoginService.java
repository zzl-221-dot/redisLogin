package service;

import com.alibaba.fastjson.JSONObject;
import entity.Users;

public interface ILoginService {
    JSONObject login(Users users);

    JSONObject registe(Users users);

    JSONObject resetPassword(Users users);

}
