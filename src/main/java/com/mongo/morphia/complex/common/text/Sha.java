package com.mongo.morphia.complex.common.text;

import java.security.MessageDigest;

/**
 * SHA消息摘要
 * 
 * @since 2010-3-19
 * @author xichu_yu
 */
class Sha extends Hash {
    /** SHA消息摘要 */
    @Override
    public byte[] hash(byte[] message) {
	try {
	    MessageDigest sha = MessageDigest.getInstance("SHA");
	    sha.update(message);
	    return sha.digest();
	} catch (Exception e) {
	    throw new RuntimeException("Sha.hash() failed!", e);
	}
    }
}