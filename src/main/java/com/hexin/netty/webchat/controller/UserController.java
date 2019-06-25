package com.hexin.netty.webchat.controller;

import com.hexin.netty.webchat.common.dto.ServiceResponse;
import com.hexin.netty.webchat.common.dto.enums.ResponseCode;
import com.hexin.netty.webchat.entity.Users;
import com.hexin.netty.webchat.service.IUserService;
import com.hexin.netty.webchat.util.Md5Util;
import com.hexin.netty.webchat.vo.ChatVo;
import com.hexin.netty.webchat.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hexin
 * @createDate 2019年06月20日 16:16:00
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("registOrLogin")
    public ServiceResponse registOrLogin(@RequestBody Users user) throws  Exception{

        //0.判断用户名密码不为空
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return ServiceResponse.createByCodeMsg(ResponseCode.ERROR.getCode(), "用户名密码不能为空");
        }
        boolean exit = userService.queryUserNameIsExits(user.getUsername());
        Users userResult = null;
        if (exit) {
            //1. 存在---登录
            userResult = userService.queryUserForLogin(user.getUsername(), Md5Util.getMD5Str(user.getPassword()));
            if (userResult == null) {
                return ServiceResponse.createByCodeMsg(ResponseCode.ERROR.getCode(), "用户名密码不正确");
            }
        } else {
            // 2. 不存在----注册
            user.setNickname("wkypniqukanxing");
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(Md5Util.getMD5Str(user.getPassword()));
            userResult = userService.saveUser(user);

        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userResult,userVo);
        return ServiceResponse.createSuccessByData(userVo);
    }

    @PostMapping("/queryFriendsById")
    public  ServiceResponse queryFriendsById(@RequestBody Users users){
        String id = users.getId();
        if(StringUtils.isEmpty(id)){
            return ServiceResponse.createByCodeMsg(ResponseCode.ERROR.getCode(), "userId为空");
        }
        return  userService.queryFriendsById(id);

    }

    @PostMapping("/queryChatMsg")
    public  ServiceResponse queryChatMsg(@RequestBody ChatVo chatVo){
        if(chatVo== null || StringUtils.isEmpty(chatVo.getSendUserId()) || StringUtils.isEmpty(chatVo.getAcceptUserId())){
            return ServiceResponse.createByCodeMsg(ResponseCode.ERROR.getCode(), "参数不正确");

        }
        return  userService.queryChatMsg(chatVo);

    }
}
