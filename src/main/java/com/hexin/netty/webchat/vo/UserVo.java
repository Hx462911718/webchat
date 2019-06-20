package com.hexin.netty.webchat.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author hexin
 * @createDate 2019年06月20日 16:48:00
 */
@Data
public class UserVo {
    @Id
    private String id;

    private String username;

    private String faceImage;

    private String faceImageBig;

    private String nickname;

    private String qrcode;
}
