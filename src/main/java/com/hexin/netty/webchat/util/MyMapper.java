package com.hexin.netty.webchat.util;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**

 * Copyright © 2018Nathan.Lee.Salvatore. All rights reserved.

 *

 * @Title: MyMapper.java

 * @Prject: imooc-muxin-mybatis

 * @Package:

 * @Description: TODO

 * @author: ZHONGZHENHUA

 * @date: 2018年11月21日 下午6:47:29

 * @version: V1.0

 */

/**
 * 继承自己的MyMapper
 *
 * @author hexin
 * @since 2018-11-21 18:48
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}