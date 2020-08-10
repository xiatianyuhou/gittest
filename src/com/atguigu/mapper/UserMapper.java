package com.atguigu.mapper;

import com.atguigu.pojo.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
    public List<User> findUserList();

    /**
     * 查询一个用户
     * @param id
     * @return
     */
    public User findUserById(Integer id);

    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user);

    /**
     * 修改用户
     * @param user
     */
    public void updateUserById(User user);

    /**
     * 删除用户
     * @param i
     */
    public void deleteUserById(int i);

    @MapKey("id")
    Map<Integer,Object> queryUsersForMap();
}
