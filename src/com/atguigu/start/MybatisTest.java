package com.atguigu.start;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MybatisTest {
    //成员变量，提取
    private SqlSessionFactory sqlSessionFactory = null;

    //提取单元测试
    //@Before 表示单元测试之气加载  @After很少用
    @Before
    public void before() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    //Hello级别测试
    @Test
    public void testMybatisl() throws Exception{
        //获取一个连接 发送Sql语句给Mysql数据库  每个从连接池获得的不一样
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取接口
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = mapper.findUserList();
        userList.forEach(user -> {
            System.out.println(user.getId());
        });
    }
    //查询一个用户
    @Test
    public void testFindUserById() throws Exception{
        //获取一个连接 发送Sql语句给Mysql数据库
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取接口
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserById(1);
        System.out.println(user);
    }
    //添加用户  同时 应该返回用户的ID
    @Test
    public void testAddUser() throws Exception{
        //获取一个连接 发送Sql语句给Mysql数据库
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取接口
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //添加
        User user = new User();
        user.setLastName("关羽");
        user.setSex(1);
        mapper.addUser(user);
        sqlSession.commit();
        sqlSession.close();

        System.out.println("用户ID："+user.getId());
    }
    //修改用户
    @Test
    public void testUpdateUser() throws Exception{
        //获取一个连接 发送Sql语句给Mysql数据库
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取接口
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //修改
        User user = new User();
        user.setId(3);
        user.setSex(0);
        user.setLastName("金智媛");
        mapper.updateUserById(user);
        sqlSession.commit();
        sqlSession.close();
    }
    //删除用户
    @Test
    public void testDeleteUser() throws Exception{
        //获取一个连接 发送Sql语句给Mysql数据库
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取接口
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //删除by id
        mapper.deleteUserById(5);
        sqlSession.commit();
        sqlSession.close();
    }
    //查询所有用户 返回值 要求是Map类型,底层是hashmap嵌套hashmap
    @Test
    public void testFindMap() throws Exception{
        //获取一个连接 发送Sql语句给Mysql数据库
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取接口
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //查询所有用户 返回值 要求是Map类型
        Map<Integer, Object> map = mapper.queryUsersForMap();
        System.out.println(map);
    }
}
