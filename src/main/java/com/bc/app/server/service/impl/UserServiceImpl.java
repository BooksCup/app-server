package com.bc.app.server.service.impl;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.User;
import com.bc.app.server.mapper.UserMapper;
import com.bc.app.server.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author zhou
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 通过登录获取用户
     *
     * @param phone    手机号
     * @param password 密码
     * @return 用户
     */
    @Override
    public User getUserByLogin(String phone, String password) {
        // 手机号密码登录
        Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        paramMap.put("phone", phone);
        paramMap.put("password", password);
        List<User> userList = userMapper.getUserByPhoneAndPassword(paramMap);
        if (CollectionUtils.isNotEmpty(userList)) {
            return userList.get(0);
        }
        return null;
    }

    /**
     * 根据用户ID获取用户列表
     *
     * @param userId 用户ID
     * @return 用户列表
     */
    @Override
    public List<User> getUserById(String userId) {
        // 手机号密码登录
        return userMapper.getUserById(userId);
    }

    /**
     * 根据手机号获取用户列表
     *
     * @param phone 手机号
     * @return 用户列表
     */
    @Override
    public List<User> getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    /**
     * 查询用户分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 用户分页信息
     */
    @Override
    public PageInfo<User> getUserPageInfo(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userPageInfo = userMapper.getUserList(paramMap);
        return new PageInfo<>(userPageInfo);
    }

    /**
     * 保存用户列表
     *
     * @param userList 用户列表
     */
    @Override
    public void addUserList(List<User> userList) {
        userMapper.addUserList(userList);
    }

    /**
     * 保存用户
     *
     * @param user 用户
     */
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    /**
     * 修改用户密码
     *
     * @param paramMap 参数map
     */
    @Override
    public void updateUserPwd(Map<String, String> paramMap) {
        userMapper.updateUserPwd(paramMap);
    }

}
