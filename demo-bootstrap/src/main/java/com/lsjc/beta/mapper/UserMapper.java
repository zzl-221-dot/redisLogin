package com.lsjc.beta.mapper;

import com.lsjc.beta.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int insert(Users record);

    int insertSelective(Users record);

    Users selectUserByCardIdAndPass(Users users);

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    int selectCountById(String userId);
    /**
     * 根据cardID查询
     * @param userCardId
     * @return
     */
    int selectCountByCardId(String userCardId);
}