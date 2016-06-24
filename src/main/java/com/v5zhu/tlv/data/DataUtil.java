package com.v5zhu.tlv.data;


/**
 * Created by zhuxl@paxsz.com on 2016/6/21.
 */
public class DataUtil {
    /**                 组装数据
     * @param len       数据长度
     * @param head      数据报文头
     * @param body      数据报文体
     * @return          完整的报文数据
     */
    public static String pkgHexStringData(String len,String head,String body){
        return Data.DATA_LENGTH+Data.DATA_HEAD+Data.DATA_BODY;
    }

    /**
     * @param merId
     * @return
     */
    public static String pkgMerId(String merId){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<merId.length();i++){
            sb.append("3"+merId.charAt(i));
        }
        return sb.toString();
    }
    public static String pkgTermId(String termId){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<termId.length();i++){
            sb.append("3"+termId.charAt(i));
        }
        return sb.toString();
    }

    public static String replaceMerId(String data,String termId) throws Exception {
        String key1=Data.MERID_KEY;
        String len1=Data.MERID_LENGTH;

        //组装商户号
        String tid=pkgMerId(termId);

        String key2=data.substring(data.indexOf(Data.MERID_KEY),data.indexOf(Data.MERID_KEY)+Data.MERID_KEY.length());
        if (key1.equals(key2)){
            String len2=data.substring(data.indexOf(Data.MERID_KEY)+Data.MERID_KEY.length(),data.indexOf(Data.MERID_KEY)+Data.MERID_KEY.length()+2);
            if(len1.equals(len2)){
                return data.substring(0,data.indexOf(Data.MERID_KEY)+Data.MERID_KEY.length()+2)
                        +
                        tid
                        +
                        data.substring((data.indexOf(Data.MERID_KEY)+Data.MERID_KEY.length()+2+Integer.parseInt(len2,16)*2),data.length());

            }else{
                throw new Exception("内部错误-len");
            }
        }else{
            throw new Exception("内部错误-key");
        }
    }

    public static String replaceTermId(String data,String termId) throws Exception {
        String key1=Data.TERMID_KEY;
        String len1=Data.TERMID_LENGTH;

        //组装商户号
        String mid=pkgTermId(termId);

        String key2=data.substring(data.indexOf(Data.TERMID_KEY),data.indexOf(Data.TERMID_KEY)+Data.TERMID_KEY.length());
        if (key1.equals(key2)){
            String len2=data.substring(data.indexOf(Data.TERMID_KEY)+Data.TERMID_KEY.length(),data.indexOf(Data.TERMID_KEY)+Data.TERMID_KEY.length()+2);
            if(len1.equals(len2)){
                return data.substring(0,data.indexOf(Data.TERMID_KEY)+Data.TERMID_KEY.length()+2)
                        +
                        mid
                        +
                        data.substring((data.indexOf(Data.TERMID_KEY)+Data.TERMID_KEY.length()+2+Integer.parseInt(len2,16)*2),data.length());

            }else{
                throw new Exception("内部错误-len");
            }
        }else{
            throw new Exception("内部错误-key");
        }
    }
    public static String replaceTraceNo(String data,String traceNo) throws Exception {
        String key1=Data.TRACENO_KEY;
        String len1=Data.TRACENO_LENGTH;

        //组装流水号
        String mid=traceNo;

        String key2=data.substring(data.indexOf(Data.TRACENO_KEY),data.indexOf(Data.TRACENO_KEY)+Data.TRACENO_KEY.length());
        if (key1.equals(key2)){
            String len2=data.substring(data.indexOf(Data.TRACENO_KEY)+Data.TRACENO_KEY.length(),data.indexOf(Data.TRACENO_KEY)+Data.TRACENO_KEY.length()+2);
            if(len1.equals(len2)){
                return data.substring(0,data.indexOf(Data.TRACENO_KEY)+Data.TRACENO_KEY.length()+2)
                        +
                        mid
                        +
                        data.substring((data.indexOf(Data.TRACENO_KEY)+Data.TRACENO_KEY.length()+2+Integer.parseInt(len2,16)*2),data.length());

            }else{
                throw new Exception("内部错误-len");
            }
        }else{
            throw new Exception("内部错误-key");
        }
    }
}
