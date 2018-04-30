package com.zyy.YingUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import com.zyy.Yggdrasil.YingYggdrasil;

/**
 * <p>
 * RSA��Կ/˽Կ/ǩ�����߰�
 * </p>
 * <p>
 * ���ɵ¡���ά˹�أ�Ron [R]ivest�������ϡ���Ī����Adi [S]hamir�������ɵ¡���������Leonard [A]dleman��
 * </p>
 * <p>
 * �ַ�����ʽ����Կ��δ������˵������¶�ΪBASE64�����ʽ<br/>
 * ���ڷǶԳƼ����ٶȼ��仺����һ���ļ���ʹ���������ܶ���ʹ�öԳƼ��ܣ�<br/>
 * �ǶԳƼ����㷨���������ԶԳƼ��ܵ���Կ���ܣ�������֤��Կ�İ�ȫҲ�ͱ�֤�����ݵİ�ȫ
 * </p>
 * 
 * @author IceWee
 * @date 2012-4-26
 * @version 1.0
 */

public class YingRSAUtils {
	//
	
	private static PrivateKey key;
	static {
		try {
			key = loadPrivateKey();
		} catch (IOException | GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	
	}
	
	private static PrivateKey loadPrivateKey() throws IOException, GeneralSecurityException {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodePrivateKey(YingYggdrasil.Ying));
		return keyFactory.generatePrivate(keySpec);
	}
	
	private static byte[] decodePrivateKey(String pem) {
		final String header = "-----BEGIN PRIVATE KEY-----\n";
		final String end = "-----END PRIVATE KEY-----\n";
		if (pem.startsWith(header) && pem.endsWith(end)) {
			return Base64.getDecoder()
					.decode(pem.substring(header.length(), pem.length() - end.length()).replace("\n", ""));
		} else {
			throw new IllegalArgumentException("Bad key format");
		}
	}
	
	public static String sign(String data)  throws Exception {
		try {
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(key, new SecureRandom());
			signature.update(data.getBytes("utf-8"));
			return Base64.getEncoder().encodeToString(signature.sign());
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
