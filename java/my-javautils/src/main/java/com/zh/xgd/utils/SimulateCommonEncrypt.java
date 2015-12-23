package com.zh.xgd.utils;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SimulateCommonEncrypt
{
  private final String keyA;
  private final String keyB;
  // 字符集
  private String encoding = "GBK";
  
  public SimulateCommonEncrypt(String encryptKey) {
	  if (encryptKey.length() != 32) {
		  throw new IllegalArgumentException("密钥初始长度错误");
	  }
	  this.keyA = encryptKey.substring(0,16);
	  this.keyB = encryptKey.substring(16);
  }
  
  /**
   * 标准DES加密
   * 使用指定密钥的前8字节进行DES计算
   * @param source
   * @param encoding
   * @return
   * @throws UnsupportedEncodingException
   */
  public String encryptDes(String source)
    throws UnsupportedEncodingException
  {
    if (source == null) {
      return null;
    }
    if (source.length() == 0) {
      return "";
    }

    try
    {
      SecretKey desKey = new SecretKeySpec(HexBinary.decode(this.keyA), "DES");
      Cipher cp = Cipher.getInstance("DES/ECB/NoPadding");
      cp.init(Cipher.ENCRYPT_MODE, desKey);

      byte[] b1 = HexBinary.decode(source);

      byte[] b2 = cp.doFinal(b1);

      StringBuilder builder = new StringBuilder();
      builder.append(HexBinary.encode(b2));
      return builder.toString();
      
    } catch (RuntimeException e) {
      throw e; 
      } catch (Exception e) {
    	  throw new RuntimeException("Encrypt failure", e);
    }
    
  }

  public String decrypt(String cipher, int type)
    throws UnsupportedEncodingException
  {
    if (cipher == null) {
      return null;
    }
    if (cipher.length() == 0) {
      return "";
    }

    try
    {
      SecretKey desKey;
      // 使用A解密
      if (type == 0) {
    	  desKey = new SecretKeySpec(HexBinary.decode(this.keyA), "DES");
      } else {
    	  desKey = new SecretKeySpec(HexBinary.decode(this.keyB), "DES");
      }
      
      Cipher cp = Cipher.getInstance("DES/ECB/NoPadding");
      // 解密
      cp.init(2, desKey);

      byte[] b1 = HexBinary.decode(cipher);
      byte[] b2 = cp.doFinal(b1);

      return new String(b2, encoding);
    } catch (RuntimeException e) {
      throw e; } catch (Exception e) {
    	  throw new RuntimeException("Decrypt failure", e);
    }
   
  }
  	/**
  	 * 16bit 3DES padding 00 cbc
  	 * @param cipher
  	 * @param encoding
  	 * @return
  	 * @throws UnsupportedEncodingException
  	 */
	public String encrypt3DES(String cipher)
			throws UnsupportedEncodingException {
		if (cipher == null) {
			return null;
		}
		if (cipher.length() == 0) {
			return "";
		}
		if (cipher.length()%8 != 0) {
			// 补位操作
			for(int i=cipher.length();i<cipher.length()+cipher.length()%8;i++){
				cipher = cipher +"0";
			}
		}
		try {
			SecretKey desKey = new SecretKeySpec(HexBinary.decode(this.keyA), "DES");
			Cipher cp = Cipher.getInstance("DES/ECB/NoPadding");
			cp.init(Cipher.ENCRYPT_MODE, desKey);

			StringBuilder builder = new StringBuilder();
			builder.append(cipher);

			byte[] b1 = HexBinary.decode(builder.toString());
			byte[] b2 = cp.update(b1);			
			SecretKey desKey2 = new SecretKeySpec(HexBinary.decode(this.keyB), "DES");
			Cipher cp2 = Cipher.getInstance("DES/ECB/NoPadding");
			cp2.init(Cipher.DECRYPT_MODE, desKey2);
			byte[] b3 = cp2.doFinal(b2);
			byte[] b4 = cp.doFinal(b3);
			Security.getProviders();
			return HexBinary.encode(b4);

/**
 * 3DES
 */
//			SecretKey desKey = new SecretKeySpec(HexBinary.decode("3016745AB289EFCDBADCFE03254769813016745AB289EFCD"), "DESede");
//			Cipher cp = Cipher.getInstance("DESede/ECB/NoPadding");
//			cp.init(Cipher.ENCRYPT_MODE, desKey);
//
//			StringBuilder builder = new StringBuilder();
//			builder.append(cipher);
//			
//			byte[] b4 = cp.doFinal(HexBinary.decode(builder.toString()));
//			return HexBinary.encode(b4);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Decrypt failure", e);
		}

	}
	
	/**
	 * 生成随机密钥
	 */
	public static String generateRandomKey() throws Exception{
		  KeyGenerator _generator = KeyGenerator.getInstance("DES");
			// 参数为种子
			//_generator.init(new SecureRandom(HexBinary.decode("12345678")));
		  _generator.init(new SecureRandom());
			SecretKey key = _generator.generateKey();
			System.out.println(HexBinary.encode(key.getEncoded()));
			return new String(HexBinary.encode(key.getEncoded()));
	}
}