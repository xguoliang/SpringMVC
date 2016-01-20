package com.mongo.morphia.complex.common.text;

/**
 * CRC32检验码
 * 
 * @since 2010-3-19
 * @author xichu_yu
 */
class Crc32 extends Hash {

    /** CRC32消息摘要 */
    @Override
    public byte[] hash(byte[] message) {
	java.util.zip.CRC32 crc = new java.util.zip.CRC32();
	crc.update(message);
	long tmp = crc.getValue();
	byte[] buffer = new byte[4];
	for (int i = 3; i >= 0; i--) {
	    buffer[i] = (byte) (tmp & 255);
	    tmp >>= 8;
	}
	return buffer;
    }
}