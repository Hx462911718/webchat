package com.hexin.netty.webchat.util;
import org.apache.tomcat.util.codec.binary.Base64;


import java.security.MessageDigest;


/**
 * @author hexin
 * @createDate 2019年06月20日 16:11:00
 * 1.MD5加密字符串（32位大写）
 * 2.MD5加密字符串（32位小写）
 * <p>
 * MD5在线加密：https://md5jiami.51240.com/
 * 3.将二进制字节数组转换为十六进制字符串
 */
public class Md5Util {

    /**
     * @Description: 对字符串进行md5加密
     */
    public static String getMD5Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
        return newstr;
    }

    public static void main(String[] args) {
        try {
            String md5 = getMD5Str("imooc");
            System.out.println(md5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
