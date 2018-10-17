package com.jf.myDemo.service;

import com.jf.wj.demo.entity.User;
import com.jf.wj.demo.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-10-16 11:10
 * @Description: myBatis userservice测试类
 * To change this template use File | Settings | File and Templates.
 */

/**
 * 在测试类中加载Spring配置的两种常见方式
 * 1.自动加载。就是当前测试类上的注解类型
 * @RunWith(SpringJUnit4ClassRunner.class)
 * @RunWith 注释标签是 Junit 提供的，用来说明此测试类的运行者，这里用了 SpringJUnit4ClassRunner，这个类是一个针对 Junit 运行环境的自定义扩展，用来标准化在 Spring 环境中 Junit4.5 的测试用例，例如支持的注释标签的标准化
 @ContextConfiguration({"classpath:spring的配置文件"})
 @ContextConfiguration 注释标签是 Spring test context 提供的，用来指定 Spring 配置信息的来源，支持指定 XML 文件位置或者 Spring 配置类名，这里我们指定 classpath 下的 /config/Spring-db1.xml 为配置文件的位置
 Note:当用多个xml文件要加载时，可以写成下面这种
 @ContextConfiguration(locations = { "classpath*:/spring1.xml", "classpath*:/spring2.xml" })
 ----延伸拓展
 @Transactional 注释标签是表明此测试类的事务启用，这样所有的测试方案都会自动的 rollback，即您不用自己清除自己所做的任何对数据库的变更了
 还支持testNG的测试，更多的内容请参考（上述注解说明也来源于下方链接）
 使用 Spring 进行单元测试
 https://www.ibm.com/developerworks/cn/java/j-lo-springunitest/index.html
 2.手动加载
 ApplicationContext context = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/applicationContext.xml");
 new ClassPathXmlApplicationContext("applicationContext.xml");// 从classpath中加载
 new FileSystemXmlApplicationContext("classpath:地址");// 没有classpath表示当前
 eg:
 // 加载spring与数据库配置的部分得到DataSource
 // ==>位置是resources下面的
 ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-config.xml");
 DataSource ds = (DataSource) context.getBean("dataSource");
 Connection connection = ds.getConnection();
 同理，也能通过这种方法获得Service类，然后调用对应的方法
 xxService service = (xxService) context.getBean("xxService");
 前提是要把service，dao，entity等通过IOC交给Spring去管理
 ---------------------
 作者：陈平寨黄山赵子龙
 来源：CSDN
 原文：https://blog.csdn.net/qq_25615395/article/details/78105097?utm_source=copy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-core.xml"})
@Transactional
public class UserServiceTest {
    /**
     * 打印日志用logger-用debug级别
     *
     */
    private Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class.getName());

    @Autowired
    private UserService userService;

    @Before
    public void loadConfig(){
        LOGGER.debug("START***********程序运行开始***********START");
    }
    @After
    public void endConfig(){
        LOGGER.debug("END***********程序运行结束***********END");
    }
    @Test
    public void userSelectTest(){
        String userId = "1000";
        String password = "123456";
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        Map<String,Object> map = new HashMap<>(4);
        map.put("userId",userId);
        map.put("password",password);
        /**
         * fetchUserById
         * 测试结果记录
         1)非userId
         user_id = #{id}
         测试结果：ok
         2）索引
         user_id = #{0}
         测试结果：ok
         3）默认的参数名
         user_id = #{param1}
         测试结果：ok
         4）用$来接收参数
         ---${_parameter}
         ok
         ---${其他字段}
         no
         报错信息：There is no getter for property named 'id' in 'class java.lang.String'
         原因：这种单个字段的，myBatis会自行的去处理，所以，不管你是传什么值，它都是成功的
         这种情况下可以直接省去parameterType类型的声明。但是，为了可读性，最好还是给加上
         */
        /*——————————————————————————————————————*/
//        User resultUser = this.userService.fetchUserById(userId);
        /**
         *  * fetchUserByMultiFields
         * 测试结果记录
         * 1)String userId, String password
         * ---在xml直接用 #{param1},#{param2}ok；
         * ---用#{0}，#{1}来接收呢？ok
         *---用#{userId}，#{password}呢？no---》这种情况下会失败，报错提示的内容为
         * Available parameters are [0, 1, param1, param2]c
         * 即上面的两种方式、
         *  [0, 1, param1, param2]还能直接被作为对象来用，即下方的fetchUserByMultiFieldsOrObjects方法
         *  取值的时候，用param1.对象属性或者0.对象属性就可以了
         */
//        this.userService.fetchUserByMultiFields(userId,password);
        this.userService.fetchUserByMultiFieldsOrObjects(userId,user);
        /*——————————————————————————————————————*/
        /**
         * fetchUserByParamId
         * 测试结果记录：
         * 1）在xml中的#{}接收参数名和注解指定的不一致
         * ---0
         *ok
         * ---param1
         *ok
         * ---其他
         *ok
         * 2）一致
         *ok
         * 由上述的测试结果，可以看出，当只有一个参数的时候，MyBatis会默认的把它当成是一个参数来处理、
         * 这种情况下，加不加@Param注解都是一样。只是呢，为了保证可读性和规范性，还是把@Param注解加上比较好
         */
//        this.userService.fetchUserByParamId(userId);
        /*——————————————————————————————————————*/
        /**
         * fetchUserByParamMultiFields
         *测试结果记录
         * 1）不一致
         * ---0或1
         *多个参数时，用参数的索引下标就有问题了，会报错
         *  Parameter '1'/'0' not found. Available parameters are [password, userId, param1, param2]
         * ---param1或param2
         *这种参数条件下，就像上方报错提示的那种，是可行的。
         * 需要的注意的是，用param时，参数的位置要对应上。不然查不出你想要的结果来。
         * ---其他
         *必须是一致或者用默认的param1和param2
         * 2）一致
         * ok
         * 综上所述，多个参数加上了@Param注解后，最好还是依着@Param注解中的别名来接收参数。
         * 这样看起来也比较能懂。
         */
//        this.userService.fetchUserByParamMultiFields(userId,password);
        /*——————————————————————————————————————*/
        /**
         * fetchUserByMap
         * 测试结果记录：
         *ok
         */
//        this.userService.fetchUserByMap(map);
        /*——————————————————————————————————————*/
        /**
         * fetchUserByBean
         * 测试结果记录：
         *ok
         */
//        this.userService.fetchUserByBean(user);
         /**
          *****************************************
          * Created with IntelliJ IDEA.
          * @Author: Wangjie
          * @Date:   2018-10-16 14:50:12
          * @Description: myBatis查询总结说明
          * 注解：值@Param("xxx")注解
          * 1.加注解/不加注解单字段类
          * 可以直接用，因为myBatis会去处理
          * 这种情况下也可以直接${_parameter}的方式去接收。
          * 内部的执行过程是怎样的呢？
          *
          * 2.不加注解多字段
          * 只能用0,1之类的参数索引下标
          * 或者是
          * param1，param2之类的由MyBatis处理的默认参数名
          * 其他的都不可以
          *
          * 3.加注解多字段
          * 只能是用注解声明的别名或者是默认生成param1，param2
          *
          * 4.map类
          * 直接用，取字段的时候，注意参数名的一致性即可
          *
          * 5.对象类
          * 同map，需要主要的是，如果是对象中的属性有其他对象的，
          * 要用a.xxx
          * a---对象中的其他对象属性
          * xxx---该对象的某一个字段
          *
          * 上述的这些，parameterType字段都不做强制要求，不加上也没有关系。
          * 但考虑到规范化，还是加上要稳妥点。
          *
          * 参数映射的源码流程还待补充。
          *
          */
    }
}
