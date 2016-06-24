package com.v5zhu.tlv.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ByteUtils {
	
	/**
	 * 
	 * @Description: 把i=123转成“123”形式的字符串，根据needLength在“123”前面加“0”
	 * @param @param i
	 * @param @param needLength
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @throws
	 */
	public static String int2Str(int i, int needLen) throws Exception {
		String src = String.valueOf(i);
		if (src.length() < needLen) {
			for (int j = 0; j < needLen - src.length(); i++) {
				src = "0" + src;
			}
		}
		return src;
	}
	
	/**
	 * 
	 * @Description: 去掉字符串“0012300”中前面的两个0
	 * @param @param src
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String strTrim0(String src) {
		
		StringBuffer buffer = new StringBuffer(src);
		;
		
		while (buffer.charAt(0) == '0') {
			buffer.deleteCharAt(0);
		}
		
		return buffer.toString();
	}
	
	/**
	 * 
	 * @Description: 在字符串后面加'\0'即空白，通过trim()方法可以去掉'\0'
	 * @param @param src
	 * @param @param length
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String fillVacancy0(String src, int length) {
		
		byte[] srcBs = src.getBytes();
		
		for (int i = 0; i < length - srcBs.length; i++) {
			src = src + '\0';
		}
		return src;
		
	}
	
	/**
	 * 
	 * @Description: 字符串转字节，不足位数在字节数组后面补0，即先在字符串后面补'\0'后再转字节
	 * @param @param str
	 * @param @param len
	 * @param @param charset
	 * @param @return
	 * @return byte[]
	 * @throws
	 */
	public static byte[] str2bytes(String str, int len, String charset, String align, String add) {
		
		byte[] dest = null;
		try {
			
			if (len == 0) {
				dest = str.getBytes(charset);
			} else {
				byte[] bs = str.getBytes(charset);
				if (len < bs.length) {
					str = str.substring(0, len);
				} else {
					if ("left".equals(align)) {
						for (int i = 0; i < len - bs.length; i++) {
							str = str + add;
						}
					} else {
						for (int i = 0; i < len - bs.length; i++) {
							str = add + str;
						}
					}
					
				}
				dest = str.getBytes(charset);
				
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return dest;
	}
	
	/**
	 * 
	 * @Description: 数字类型的字符串转字节，不足位数时在字符串前面补"0"后再转字节
	 * @param @param str
	 * @param @param len
	 * @param @param charset
	 * @param @return
	 * @return byte[]
	 * @throws
	 */
	public static byte[] nStr2bytes(String nStr, int len, String charset, String align, String add) {
		
		byte[] dest = null;
		
		try {
			if (len == 0) {
				dest = nStr.getBytes(charset);
			} else {
				byte[] bs = nStr.getBytes(charset);
				if (len < bs.length) {
					nStr = nStr.substring(0, len);
				}
				if ("left".equals(align)) {
					for (int i = 0; i < len - bs.length; i++) {
						nStr = nStr + add;
					}
				} else {
					for (int i = 0; i < len - bs.length; i++) {
						nStr = add + nStr;
					}
				}
				
				dest = nStr.getBytes(charset);
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return dest;
	}
	
	public static byte[] int2bytesdq(int i, int length) {
		byte[] bytes = new byte[length];
		for (int j = 0; j < length; j++) {
			bytes[j] = (byte) ((i >> (j * 8)) & 0xff);
		}
		return bytes;
	}
	
	/**
	 * 
	 * @Description: 把一个int转化成指定长度的字节数组,int的高位放在字节数组的0处
	 * @param @param i
	 * @param @param length
	 * @param @return
	 * @return byte[]
	 * @throws
	 */
	public static byte[] int2bytes(int i, int length) {
		
		byte[] bytes = new byte[4];
		bytes[3] = (byte) (i & 0xff);
		bytes[2] = (byte) ((i & 0xff00) >> 8);
		bytes[1] = (byte) ((i & 0xff0000) >> 16);
		bytes[0] = (byte) ((i & 0xff000000) >> 24);
		byte[] dest = new byte[length];
		
		int bytesLen = 4;
		for (int j = length - 1; j >= 0; j--) {
			dest[j] = bytes[--bytesLen];
		}
		
		return dest;
	}
	
	/**
	 * 
	 * @Description: 把一个int转化成字节数组,可能是1个字节、2个字节、4个字节，int的高位放在字节数组的0处
	 * @param @param i
	 * @param @param length
	 * @param @return
	 * @return byte[]
	 * @throws
	 */
	public static byte[] int2bytes(int i) {
		
		byte[] bytes = new byte[4];
		bytes[3] = (byte) (i & 0xff);
		bytes[2] = (byte) ((i & 0xff00) >> 8);
		bytes[1] = (byte) ((i & 0xff0000) >> 16);
		bytes[0] = (byte) ((i & 0xff000000) >> 24);
		
		List<Byte> list = new ArrayList<Byte>();
		int m = 0;
		for (int j = 0; j < bytes.length; j++) {
			if (bytes[j] != 0) {
				m = j;
				break;
			}
		}
		for (int k = m; k < bytes.length; k++) {
			list.add(bytes[k]);
		}
		
		byte[] dest = new byte[list.size()];
		for (int j = 0; j < dest.length; j++) {
			dest[j] = list.get(j);
		}
		
		return dest;
	}
	
	/**
	 * 
	 * @Description: 字节数组转整数，字节数组的0处表示整数的高位
	 * @param @param res
	 * @param @return
	 * @return int
	 * @throws
	 */
	public static int bytes2int(byte[] res) {
		
		int length = res.length;
		int targets = 0;
		if (length == 1) {
			targets = (res[0] & 0xff);
		} else if (length == 2) {
			targets = (res[1] & 0xff) | ((res[0] << 8) & 0xff00);
		} else if (length == 3) {
			targets = (res[2] & 0xff) | ((res[1] << 8) & 0xff00) | ((res[0] << 16) & 0xff0000);
		} else {
			targets = (res[3] & 0xff) | ((res[2] << 8) & 0xff00) | ((res[1] << 16) & 0xff0000)
						| ((res[0] << 24) & 0xff000000);
		}
		
		return targets;
	}
	
	/*public static int bytes2intdq(byte[] res){
		
		
		int length = res.length;
		int targets = 0;
		if (length==1) {
			targets = (res[0] & 0xff);
		}else if (length==2) {
			targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00);
		}else if (length==3) {
			targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00)  
			| ((res[2] << 16)&0xff0000);
		}else {
			targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00)
			| ((res[2] << 16)&0xff0000) | ((res[3] << 24)&0xff000000); 
		}
		
		return targets; 
		
	}*/

	public static String bytes2HexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes) {
			String hex = Integer.toHexString(b & 0xff);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hex = hex.toUpperCase();
			sb.append(hex);
		}
		return sb.toString();
	}
	
	public static byte[] hexString2bytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (char2byte(hexChars[pos]) << 4 | char2byte(hexChars[pos + 1]));
		}
		return d;
	}
	
	public static byte char2byte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	/**
	    * @函数功能: BCD码转为十六进制串
	    * @输入参数: BCD码
	    * @输出结果: 十六进制串
	    */
	public static String bcd2hexStr(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		
		for (int i = 0; i < bytes.length; i++) {
			temp.append(Integer.toHexString(((bytes[i] & 0xf0) >>> 4)));
			temp.append(Integer.toHexString((bytes[i] & 0x0f)));
		}
		return temp.toString().toUpperCase();
	}
	
	/**
	* @函数功能: 十六进制串转为BCD码
	* @输入参数: 十六进制串
	* @输出结果: BCD码
	*/
	public static byte[] hexStr2bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;
		
		if (mod != 0) {
			//asc = "0" + asc;
			asc = asc + "F";
			len = asc.length();
		}
		
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}
	
	public static byte[] getBytes(byte[] source, int start, int length) {
		byte[] dest = new byte[length];
		int j = 0;
		for (int i = start; i < start + length; i++) {
			dest[j++] = source[i];
		}
		return dest;
	}
	
	public static byte[] bu0(String src, int byteLength) throws UnsupportedEncodingException {
		
		byte[] bs = new byte[byteLength];
		byte[] bs2 = src.getBytes("GBK");
		System.arraycopy(bs2, 0, bs, 0, bs2.length);
		for (int i = bs.length; i < byteLength; i++) {
			bs[i] = 0;
		}
		return bs;
	}
	
	public static String bytes2byteString(byte[] bytes) {
		StringBuffer buffer = new StringBuffer("");
		for (byte b : bytes) {
			buffer.append(b);
		}
		return buffer.toString();
		
	}
	
	public static byte asc_to_bcd(byte asc) {
		byte bcd;
		
		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}
	
	public static byte[] bcd_to_asc(byte[] bytes) {
		byte[] temp = new byte[bytes.length * 2];
		int h, l;
		
		for (int i = 0; i < bytes.length; i++) {
			h = ((bytes[i] & 0xf0) >>> 4);
			l = (bytes[i] & 0x0f);
			temp[i * 2] = BToA(h);
			temp[i * 2 + 1] = BToA(l);
		}
		return temp;
	}
	
	private static byte BToA(int src) {
		byte dest;
		if (src <= 9)
			dest = new Integer(src + 0x30).byteValue();
		else
			dest = new Integer(src + 0x37).byteValue();
		return dest;
	}
	
	public static byte[] ascs_to_bcds(byte[] ascii, int asc_len) {
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}
	
	public static String bcd2Str(byte[] bytes) {
		char temp[] = new char[bytes.length * 2], val;
		
		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
			
			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}
	
	public static void main2(String[] args) {
		
		byte b = 50;
		byte b2 = asc_to_bcd(b);
		System.out.println(b2);
		
		byte[] b3 = bcd_to_asc(new byte[] { 2 });
		
		System.out.println(new String(b3));
		
	}
	public static void main(String[] args) {

		String hex = "009c6000100000654000400100ff90000404005101ff900303000002ff9011083531343030313636ff90120f303030303030303030303430303033ff902203000001ff902302004fff90410101ff80422710000642000063300000340100054020005403000540400054050005406000540700054080005fff80410101ff90551d53657175656e6365204e6f3136333135305358582d3443333632333932";
		byte[] bs = hexString2bytes(hex);

		byte[] bs2 = ascs_to_bcds(bs, bs.length);

		System.out.println(bs.length);
		System.out.println(bs2.length);

		System.out.println(bytes2HexString(bs2));
	}
}
