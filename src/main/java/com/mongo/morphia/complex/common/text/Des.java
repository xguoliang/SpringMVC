package com.mongo.morphia.complex.common.text;
/**
 * DES加密/解密
 * 
 * @since 2010-3-19
 * @author xichu_yu
 */
class Des extends Cipher {
    /** 加密 */
    @Override
    public byte[] encrypt(byte[] data, String key) {
        return transform(data, key, javax.crypto.Cipher.ENCRYPT_MODE);
    }

    /** 解密 */
    @Override
    public byte[] decrypt(byte[] data, String key) {
        return transform(data, key, javax.crypto.Cipher.DECRYPT_MODE);
    }

    /** 转换 */
    public byte[] transform(byte[] data, String key, int mode) {
        try {
            javax.crypto.spec.SecretKeySpec spec = string2key(key);
            javax.crypto.Cipher ci = javax.crypto.Cipher.getInstance("DES");
            ci.init(mode, spec);
            return ci.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Des.transform() failed!", e);
        }
    }

    /** 根据字符串s生成密钥 */
    private javax.crypto.spec.SecretKeySpec string2key(String key) {
        try {
            // key的MD5摘要
            byte[] tmp = Hash.MD5.hash(Encoding.stringToUtf8(key));
            byte[] bkey = new byte[8];
            for (int i = 0; i < 8; i++)
                bkey[i] = tmp[i + 4];
            return new javax.crypto.spec.SecretKeySpec(bkey, "DES");
        } catch (Exception e) {
            throw new RuntimeException("Des.string2key() failed!", e);
        }
    }
}