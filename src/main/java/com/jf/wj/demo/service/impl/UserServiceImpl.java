package com.jf.wj.demo.service.impl;

import com.jf.wj.demo.dao.UserDao;
import com.jf.wj.demo.entity.User;
import com.jf.wj.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-10-16 10:53
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */
@Service("userService")
public class UserServiceImpl  implements UserService{

    /**
     * 打印日志用logger-用debug级别
     *
     */
    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class.getName());


    @Autowired
    private UserDao userDao;

    @Override
    public User fetchUserById(String userId) {
        User user = this.userDao.queryUserById(userId);
        LOGGER.debug("fetchUserByid查询到的用户为:"+user.toString());
        return user;
    }

    @Override
    public User fetchUserByMultiFields(String userId, String password) {
        User user = this.userDao.queryUserByMultiFields(userId,password);
        LOGGER.debug("fetchUserByMultiFields查询到的用户为:"+user.toString());
        return user;
    }

    @Override
    public User fetchUserByParamId(String userId) {
        User user = this.userDao.queryUserById(userId);
        LOGGER.debug("fetchUserByParamid查询到的用户为:"+user.toString());
        return user;
    }

    @Override
    public User fetchUserByParamMultiFields(String userId, String password) {
        User user = this.userDao.queryUserByParamMultiField(userId,password);
        LOGGER.debug("fetchUserByParamMultiField查询到的用户为:"+user.toString());
        return user;
    }

    @Override
    public User fetchUserByBean(User queryUser) {
        User user = this.userDao.queryUserByBean(queryUser);
        LOGGER.debug("fetchUserByBean查询到的用户为:"+user.toString());
        return user;
    }

    @Override
    public User fetchUserByMap(Map<String, Object> queryMap) {
        User user = this.userDao.queryUserByMap(queryMap);
        LOGGER.debug("fetchUserByMap查询到的用户为:"+user.toString());
        return user;
    }
}
