package com.jf.wj.demo.service;

import com.jf.wj.demo.entity.User;

import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-10-16 10:53
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public interface UserService {
    /**
     * 单字段
     * @param userId
     * @return
     */
    User fetchUserById(String userId);

    /**
     * 多字段查询
     * @param userId
     * @param password
     * @return
     */
    User fetchUserByMultiFields(String userId, String password);


    /**
     * 单字段-加了@Param注解
     * @param userId
     * @return
     */
    User fetchUserByParamId(String userId);

    /**
     * 多字段查询-加了@Param注解
     * @param userId
     * @param password
     * @return
     */
    User fetchUserByParamMultiFields(String userId, String password);


    /**
     * 实体对象
     * @param queryUser
     * @return
     */
    User fetchUserByBean(User queryUser);

    /**
     * map对象
     * @param queryMap
     * @return
     */
    User fetchUserByMap(Map<String,Object> queryMap);
}
