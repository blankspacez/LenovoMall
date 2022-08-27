package com.mall.lenovoMall.service;

import com.mall.lenovoMall.exception.ExceptionEnum;
import com.mall.lenovoMall.exception.XmException;
import com.mall.lenovoMall.mapper.UserMapper;
import com.mall.lenovoMall.entity.User;
import com.mall.lenovoMall.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(User user) {
        user.setPassword(MD5Util.MD5Encode(user.getPassword() + "", "UTF-8"));
        User one = userMapper.selectOne(user);
        if (one == null) {
            throw new XmException(ExceptionEnum.GET_USER_NOT_FOUND);
        }
        return one;
    }

    public void register(User user) {
        User one = new User();
        one.setUsername(user.getUsername());
        // 先去看看用户名是否重复
        if (userMapper.selectCount(one) == 1) {
            // 用户名已存在
            throw new XmException(ExceptionEnum.SAVE_USER_REUSE);
        }
        // 使用md5对密码进行加密
        user.setPassword(MD5Util.MD5Encode(user.getPassword() + "", "UTF-8"));
        // 存入数据库
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.SAVE_USER_ERROR);
        }
    }

    public void isUserName(String username) {
        User one = new User();
        one.setUsername(username);
        // 先去看看用户名是否重复
        if (userMapper.selectCount(one) == 1) {
            // 用户名已存在
            throw new XmException(ExceptionEnum.SAVE_USER_REUSE);
        }
    }
}
