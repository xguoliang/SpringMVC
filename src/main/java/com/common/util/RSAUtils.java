package com.common.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 利用RSA进行 1)公钥/私钥的生产 2)加密/解密 3)签名/验证签名
 * 
 */
public class RSAUtils {
	/**
	 * 生成非对称密钥对 通过 KeyPair.getPublic();获得公钥PublicKey 通过
	 * KeyPair.getPrivate();获得私钥PrivateKey 通过PublicKey(PrivateKey).getEncoded();
	 * 获得Key的编码后的byte内容，其中 公钥是通过X.509格式编码的， 私钥是通过PKCS#8编码的， 恢复的时候也要通过这两种编码格式恢复
	 */
	public static KeyPair genKeyPair() throws Exception {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(1024);
		return kpg.genKeyPair();
	}

	/**
	 * 通过byte数组恢复一个私钥 bytes[]：私钥编码后的数组
	 */
	public static PrivateKey restorePrivateKey(byte[] bytes) throws Exception {
		PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(pkcs);
	}

	/**
	 * 通过byte数组恢复一个公钥 bytes[]：共钥编码后的数组
	 */
	public static PublicKey restorePublicKey(byte[] bytes) throws Exception {
		X509EncodedKeySpec pkcs = new X509EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(pkcs);
	}

	/**
	 * 加密
	 * @param src 要加密的明文
	 * @param key 公钥/私钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(src);
	}

	/**
	 * 解密 dest
	 * @param dest 要解密的密文
	 * @param key 私钥/公钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] deEncrypt(byte[] dest, Key key)
			throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(dest);
	}

	/**
	 * 利用私钥签名 src:要签名的内容
	 */
	public static byte[] sign(byte[] src, PrivateKey prvKey) throws Exception {
		Signature sig = Signature.getInstance("MD5withRSA");
		sig.initSign(prvKey);
		sig.update(src);
		return sig.sign();
	}

	/**
	 * 利用公钥验证签名 src:签名的内容 dest:签名
	 */
	public static boolean verifySign(byte[] src, byte[] dest, PublicKey pubKey)
			throws Exception {
		Signature sig = Signature.getInstance("MD5withRSA");
		sig.initVerify(pubKey);
		sig.update(src);
		return sig.verify(dest);
	}

	public static byte[] encryptLarger(byte[] data, Key key) throws Exception {
		javax.crypto.Cipher rsa = javax.crypto.Cipher.getInstance("RSA");
		rsa.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
		SecureRandom random = new SecureRandom();
		final byte[] secretKey = new byte[16];
		random.nextBytes(secretKey);
		final javax.crypto.Cipher aes = javax.crypto.Cipher.getInstance("AES");
		SecretKeySpec k = new SecretKeySpec(secretKey, "AES");
		aes.init(javax.crypto.Cipher.ENCRYPT_MODE, k);
		final byte[] ciphedKey = rsa.doFinal(secretKey);
		final byte[] ciphedData = aes.doFinal(data);
		byte[] result = new byte[128 + ciphedData.length];
		System.arraycopy(ciphedKey, 0, result, 0, 128);
		System.arraycopy(ciphedData, 0, result, 128, ciphedData.length);
		return result;
	}

	public static byte[] deEncryptLarger(byte[] data, Key key) throws Exception {
		javax.crypto.Cipher rsa = javax.crypto.Cipher.getInstance("RSA");
		rsa.init(javax.crypto.Cipher.DECRYPT_MODE, key);
		// 恢复key
		byte[] secretKeyData = new byte[128];
		System.arraycopy(data, 0, secretKeyData, 0, secretKeyData.length);
		byte[] cipherData = new byte[data.length - secretKeyData.length];
		System.arraycopy(data, secretKeyData.length, cipherData, 0, cipherData.length);
		byte[] secretKey = rsa.doFinal(secretKeyData);
		final javax.crypto.Cipher aes = javax.crypto.Cipher.getInstance("AES");
		SecretKeySpec k = new SecretKeySpec(secretKey, "AES");
		aes.init(javax.crypto.Cipher.DECRYPT_MODE, k);
		final byte[] deciphedData = aes.doFinal(cipherData);
		return deciphedData;
	}
}
