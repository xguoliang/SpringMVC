package com.mongo.morphia.complex.common.text;

/**
 * 消息摘要
 * 
 * @since 2010-3-19
 * @author xichu_yu
 */
public abstract class Hash {
    public static final Hash CRC32 = new Crc32();
    public static final Hash MD5 = new Md5();
    public static final Hash SHA = new Sha();

    /** 获取字符串的摘要 */
    public String hash(String message) {
        byte[] bytes = Encoding.stringToUtf8(message);
        byte[] tmp = hash(bytes);
        return Encoding.utf8ToString(Encoding.HEX.encode(tmp));
    }

    /** 获取消息摘要 */
    public abstract byte[] hash(byte[] message);

    public static void main(String[] args){
        String msg="message";
        System.out.println(CRC32.hash(msg));
        System.out.println(MD5.hash(msg));
        System.out.println(SHA.hash(msg));
    }
}