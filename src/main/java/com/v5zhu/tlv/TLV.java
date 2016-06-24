package com.v5zhu.tlv;


import com.v5zhu.tlv.data.Data;
import com.v5zhu.tlv.data.DataUtil;
import com.v5zhu.tlv.net.TCPUtil;
import com.v5zhu.tlv.util.ByteUtils;

import java.util.Map;

/**
 * Created by zhuxl@paxsz.com on 2016/6/22.
 */
public class TLV{

    public static String signIn(String ip,int port,String merId,String termId,String traceNo) throws Exception {
        String data= DataUtil.pkgHexStringData(Data.DATA_LENGTH,Data.DATA_HEAD,Data.DATA_BODY);
        System.out.println("data:"+data);
        data=DataUtil.replaceMerId(data,merId);
        data=DataUtil.replaceTermId(data,termId);
        data=DataUtil.replaceTraceNo(data,traceNo);
        byte[] bs = ByteUtils.hexString2bytes(data);
        Map<String, String> respMap= TCPUtil.sendTCPRequest(ip,port,bs);
        System.out.println("签到应答："+respMap.toString());
        String response=respMap.get("respData");
        if(!"timeout".equals(response)) {
            String key = "ff9010";
            int start = response.indexOf(key) + 6 + 2;
            String result = response.substring(start, start + 2 * 2);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < result.length(); i++) {
                if (i % 2 != 0) {
                    sb.append(result.charAt(i));
                }
            }
            return sb.toString();
        }else{
            return response;
        }
    }

}
