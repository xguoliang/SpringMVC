package com.mongo.morphia.complex.common.text;

import java.security.MessageDigest;

/**
 * MD5消息摘要
 * 
 * @since 2010-3-19
 * @author xichu_yu
 */
class Md5 extends Hash {
    /** MD5消息摘要 */
    @Override
    public byte[] hash(byte[] message) {
	try {
	    MessageDigest md5 = MessageDigest.getInstance("MD5");
	    md5.update(message);
	    return md5.digest();
	} catch (Exception e) {
	    throw new RuntimeException("Md5.hash() failed!", e);
	}
    }
}