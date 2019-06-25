package com.hexin.netty.webchat.service.impl;

import com.hexin.netty.webchat.common.dto.ServiceResponse;
import com.hexin.netty.webchat.dao.ChatMsgMapper;
import com.hexin.netty.webchat.dao.MyFriendsMapper;
import com.hexin.netty.webchat.dao.UsersMapper;
import com.hexin.netty.webchat.entity.ChatMsg;
import com.hexin.netty.webchat.entity.MyFriends;
import com.hexin.netty.webchat.entity.Users;
import com.hexin.netty.webchat.service.IUserService;
import com.hexin.netty.webchat.util.UUIDUtil;
import com.hexin.netty.webchat.vo.ChatVo;
import com.hexin.netty.webchat.vo.UserVo;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author hexin
 * @createDate 2019年06月20日 16:23:00
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private MyFriendsMapper myFriendsMapper;
    @Autowired
    private ChatMsgMapper chatMsgMapper;


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

    @Override
    public ServiceResponse queryFriendsById(String id) {

        Example example = new Example(MyFriends.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("myUserId",id);
        List<MyFriends> list = myFriendsMapper.selectByExample(example);
        List<UserVo> result = new ArrayList<>();
        for(MyFriends item : list){
            UserVo userVo = new UserVo();
            Users users = new Users();
            users.setId(item.getMyFriendUserId());
            Users info = usersMapper.selectOne(users);
            BeanUtils.copyProperties(info,userVo);
            result.add(userVo);
        }
        return ServiceResponse.createSuccessByData(result);
    }

    @Override
    public ServiceResponse queryChatMsg(ChatVo chatVo) {
       List<Map> list =  chatMsgMapper.queryChatMsg(chatVo);
        return ServiceResponse.createSuccessByData(list);
    }
}
