package com.common.util.security;


import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;  
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;  
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;  
  

/**
 * 加密配置文件
 * @author lhw
 */
  
public class DesUtil {  
	 
	 private static Logger log = LoggerFactory.getLogger(DesUtil.class);
	
	 private static final byte[] DES_KEY_BAK = { 21, 1, -110, 82, -32, -85, -128, -65 };
	  private static final byte[] DES_KEY = "Lai8206Zx".getBytes();
	  private static final String DES = "DES";
	  
	  /**
	   * 加密
	   * @param data
	   * @return
	   */
	  public static String encryptBasedDes(String data)
	  {
	    String encryptedData = null;
	    try
	    {
	      SecureRandom sr = new SecureRandom();
	      DESKeySpec deskey = new DESKeySpec(DES_KEY);

	      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	      SecretKey key = keyFactory.generateSecret(deskey);

	      Cipher cipher = Cipher.getInstance("DES");
	      cipher.init(1, key, sr);

	      encryptedData = new BASE64Encoder().encode(cipher.doFinal(data
	        .getBytes()));
	    } catch (Exception e) {
	      log.error("加密错误，错误信息：", e);
	      throw new RuntimeException("加密错误，错误信息：", e);
	    }
	    return encryptedData;
	  }

	  /**
	   * 解密
	   * @param cryptData
	   * @return
	   */
	  public static String decryptBasedDes(String cryptData)
	  {
	    String decryptedData = null;
	    try
	    {
	      SecureRandom sr = new SecureRandom();
	      DESKeySpec deskey = new DESKeySpec(DES_KEY);

	      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	      SecretKey key = keyFactory.generateSecret(deskey);

	      Cipher cipher = Cipher.getInstance("DES");
	      cipher.init(2, key, sr);

	      decryptedData = new String(cipher
	        .doFinal(new BASE64Decoder()
	        .decodeBuffer(cryptData)));
	    } catch (Exception e) {
	      log.error("解密错误，错误信息：", e);
	      throw new RuntimeException("解密错误，错误信息：", e);
	    }
	    return decryptedData;
	  }

	  public static byte[] encrypt(byte[] src, byte[] key)
	    throws Exception
	  {
	    SecureRandom sr = new SecureRandom();

	    DESKeySpec dks = new DESKeySpec(key);

	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	    SecretKey securekey = keyFactory.generateSecret(dks);

	    Cipher cipher = Cipher.getInstance("DES");

	    cipher.init(1, securekey, sr);

	    return cipher.doFinal(src);
	  }

	  public static void main(String[] arg) {
	    try {
	      System.out.println(decryptBasedDes("QD4DdtcJURvt5d80NSu/kw=="));
	      System.out.println(encryptBasedDes("1"));
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
}