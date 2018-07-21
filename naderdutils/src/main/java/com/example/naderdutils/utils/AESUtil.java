package com.example.naderdutils.utils;




import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密处理类
 */
public class AESUtil {

	private static final String IV_STRING = "16-Bytes--String";

	private static final String PASSWORD_KEY1 = "EH77df#hj5232#df";
	private static String PASSWORD_KEY = "";//加密

	public static String encryptAES(String content) throws Exception {

		byte[] byteContent = content.getBytes("UTF-8");
		// 为了能与 iOS统一,这里的 key不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
		byte[] enCodeFormat = PASSWORD_KEY.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
		byte[] initParam = IV_STRING.getBytes();
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] encryptedBytes = cipher.doFinal(byteContent);
		String encode1 = Base64Encoder.encode(encryptedBytes);
        return encode1.replaceAll("\\s*", "");

	}
	public static String encryptAES1(String content) throws Exception {
		byte[] byteContent = content.getBytes("UTF-8");
		// 为了能与 iOS统一,这里的 key不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
		byte[] enCodeFormat = PASSWORD_KEY1.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
		byte[] initParam = IV_STRING.getBytes();
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] encryptedBytes = cipher.doFinal(byteContent);
		String encode1 = Base64Encoder.encode(encryptedBytes);
        return encode1;


	}
	public static String decryptAES(String content) throws Exception {
        byte[] bytes = Base64Decoder.decodeToBytes(content);
        byte[] enCodeFormat = PASSWORD_KEY.getBytes();
		SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
		byte[] initParam = IV_STRING.getBytes();
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		byte[] result = cipher.doFinal(bytes);
		return new String(result, "UTF-8");
	}

	public static void main(String[] args) throws Exception {
        /*System.out.println(AESUtil.encryptAES("123456"));
		System.out.println(AESUtil.decryptAES("u0XIOdBd8xIzQTGBKVhkew=="));*/
	}

}
