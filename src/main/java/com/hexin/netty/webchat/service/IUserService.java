package com.hexin.netty.webchat.service;

import com.hexin.netty.webchat.common.dto.ServiceResponse;
import com.hexin.netty.webchat.entity.FriendsRequest;
import com.hexin.netty.webchat.entity.Users;
import com.hexin.netty.webchat.vo.ChatVo;

/**
 * @author hexin
 * @createDate 2019年06月20日 16:21:00
 */
public interface IUserService {

    /**
     * 查询用户名是否存在
     *
     * @param userName
     * @return
     */
     boolean queryUserNameIsExits(String userName);

    /**
     * 验证密码
     * @param userName
     * @param password
     * @return
     */
     Users queryUserForLogin(String userName,String password);

    /**
     * 保存用户
     * @param userResult
     * @return
     */
     Users saveUser(Users userResult);


    ServiceResponse queryFriendsById(String id);

    ServiceResponse queryChatMsg(ChatVo chatVo);

    ServiceResponse queryUserByNickname(Users users);

    int friendsRequest(FriendsRequest friendsRequest);
}
