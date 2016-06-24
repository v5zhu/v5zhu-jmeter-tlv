package com.v5zhu.tlv.data;

/**
 * Created by zhuxl@paxsz.com on 2016/6/22.
 */
public class Data {
    public static final String DATA_LENGTH="009c";
    /**
     * 发送后台请求数据报文头
     */
    public static final String DATA_HEAD="6000100000654000400100";
    /**
     * 发送后台请求数据报文体
     */
    public static final String DATA_BODY="ff90000404005101ff900303000002ff9011083531343030313636ff90120f303030303030303030303430303033ff902203000001ff902302004fff90410101ff80422710000642000063300000340100054020005403000540400054050005406000540700054080005fff80410101ff90551d53657175656e6365204e6f3136333135305358582d3443333632333932";
    /**
     * 后台服务器IP地址
     */
//    public static final String IP="192.168.13.120";
    public static final String IP="192.168.16.201";
    /**
     * 后台服务器端口
     */
    public static final int PORT=37019;
    /**
     * 请求体中商户号变量名称
     */
    public static final String MERID_KEY="ff9012";
    /**
     * 商户号长度，十六进制类型
     */
    public static final String MERID_LENGTH="0f";
    /**
     * 请求体中终端号变量名称
     */
    public static final String TERMID_KEY="ff9011";
    /**
     * 终端号长度，十六进制类型
     */
    public static final String TERMID_LENGTH="08";
    /**
     * 请求体中交易流水号变量名称
     */
    public static final String TRACENO_KEY="ff9003";
    /**
     * 交易流水号长度，十六进制类型
     */
    public static final String TRACENO_LENGTH="03";
}
