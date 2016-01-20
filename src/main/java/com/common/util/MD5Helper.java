package com.common.util;

import java.security.MessageDigest;

public class MD5Helper {

	public static String MD5(String src,String charset) throws Exception {
		
		if(StringUtils.isEmpty(src))
			return null;
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(src.getBytes(charset));
		return hex(md.digest()).toLowerCase();
	}

	public static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
					3));
		}
		return sb.toString();
	}
}