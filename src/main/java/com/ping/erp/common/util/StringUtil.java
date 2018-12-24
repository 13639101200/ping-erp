package com.ping.erp.common.util;

import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;

/**
 * 字符串工具类
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public class StringUtil {

	/**
	 * 获取UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 获取字符串的MD5值
	 * 
	 * @param message
	 * @return
	 */
	public static String getMD5(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		Digest digest = new MD5Digest();
		digest.update(str.getBytes(), 0, str.getBytes().length);
		byte[] md5Byte = new byte[digest.getDigestSize()];
		digest.doFinal(md5Byte, 0);

		return Hex.encodeHexString(md5Byte).toUpperCase();
	}

	/**
	 * 是否为整型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 大驼峰转换
	 * 
	 * @param str
	 * @return
	 */
	public static String humpConverUpper(String str) {
		String[] arrStr = str.split("_");
		String newStr = "";
		for (String s : arrStr) {
			newStr += s.substring(0, 1).toUpperCase() + s.substring(1);
		}
		return newStr;
	}

	/**
	 * 小驼峰转换
	 * 
	 * @param str
	 * @return
	 */
	public static String humpConverLower(String str) {
		str = humpConverUpper(str);
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

}
