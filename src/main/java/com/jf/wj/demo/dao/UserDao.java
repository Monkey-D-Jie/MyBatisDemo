package com.jf.wj.demo.dao;

import com.jf.wj.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-10-16 11:11
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public interface UserDao {
    /**
     * 单字段
     * @param userId
     * @return
     */
    User queryUserById(@Param("userId") String userId);

    /**
     * 单个对象
     * @param user
     * @return
     */
    User queryUserBySingleObject(User user);
    /**
     * 多字段查询
     * @param userId
     * @param password
     * @return
     */
    User queryUserByMultiFields(String userId, String password);

    /**
     * 多字段或者对象
     * @param userId
     * @param user
     * @return
     */
    User queryUserByMultiFieldsOrObjects(String userId, User user);
    /**
     * 单字段-加了@Param注解
     * @param userId
     * @return
     */
    User queryUserByParamId(@Param("userId")String userId);

    /**
     * 多字段查询-加了@Param注解
     * @param userId
     * @param password
     * @return
     */
    User queryUserByParamMultiField(@Param("userId")String userId,@Param("password")String password);


    /**
     * 实体对象
     * @param queryUser
     * @return
     */
    User queryUserByBean(User queryUser);

    /**
     * map对象
     * @param queryMap
     * @return
     */
    User queryUserByMap(Map<String,Object> queryMap);
}
