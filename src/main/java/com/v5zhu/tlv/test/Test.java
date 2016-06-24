package com.v5zhu.tlv.test;

import com.pax.tlv.TLV;

/**
 * Created by zhuxl@paxsz.com on 2016/6/22.
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        String ip="192.168.16.201";
        String ip="192.168.13.120";
        int port=37019;
        String merId="000000000040003";
        String termId="51400166";
        String traceNo="000002";
        String response=TLV.signIn(ip,port,merId,termId,traceNo);
        System.out.println("签到返回:"+response);
    }
}
