package com.hexin.netty.webchat.service.impl;

import com.hexin.netty.webchat.dao.UsersMapper;
import com.hexin.netty.webchat.entity.Users;
import com.hexin.netty.webchat.service.IUserService;
import com.hexin.netty.webchat.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author hexin
 * @createDate 2019年06月20日 16:23:00
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUserNameIsExits(String userName) {
        Users users = new Users();
        users.setUsername(userName);
        Users result = usersMapper.selectOne(users);

        return result != null ? true : false;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String userName, String password) {

        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username",userName).andEqualTo("password",password);
        Users users = usersMapper.selectOneByExample(userExample);
        return users;
    }

    @Override
    public Users saveUser(Users userResult) {
        userResult.setId(UUIDUtil.getUUID());
        //todo 生成一个唯一的二维码
        userResult.setQrcode("");
        usersMapper.insert(userResult);
        return userResult;
    }
}
