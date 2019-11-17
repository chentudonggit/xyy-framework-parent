package com.xyy.framework.common.utils.rsa;

import com.xyy.framework.common.utils.AssertUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RsaUtils
 * content 要加密的内容
 * publicKey 公钥
 *
 * @author chentudong
 * @date 2019/11/9 22:05
 * @since 1.0
 */
public class RsaUtils
{
    /**
     * "RSA"="RSA/ECB/PKCS1Padding"
     */
    private static final String CIPHER_INSTANCE = "RSA/ECB/PKCS1Padding";

    /**
     * 公钥加密
     *
     * @param content   content
     * @param publicKey publicKey
     * @return String
     */
    public static String encrypt(String content, PublicKey publicKey)
    {
        AssertUtils.isNull(content, "content 不能为空");
        AssertUtils.isNull(publicKey, "publicKey 不能为空");
        try
        {
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(content.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(output);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 公钥加密
     *
     * @param content   content
     * @param publicKey publicKey
     */
    public static byte[] encrypt(byte[] content, PublicKey publicKey)
    {
        AssertUtils.isNull(content, "content 不能为空");
        AssertUtils.isNull(publicKey, "publicKey 不能为空");
        try
        {
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(content);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 私钥解密
     *
     * @param content    content
     * @param privateKey privateKey
     */
    public static byte[] decrypt(byte[] content, PrivateKey privateKey)
    {
        AssertUtils.isNull(content, "content 不能为空");
        AssertUtils.isNull(privateKey, "privateKey 不能为空");
        try
        {
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(content);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 私钥解密
     *
     * @param content    content
     * @param privateKey privateKey
     */
    public static String decrypt(String content, PrivateKey privateKey)
    {
        AssertUtils.isNull(content, "content 不能为空");
        AssertUtils.isNull(privateKey, "privateKey 不能为空");
        try
        {
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] b = cipher.doFinal(content.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(b);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * String转公钥PublicKey
     *
     * @param key 公钥字符
     */
    public static RSAPublicKey getPublicKey(String key) throws Exception
    {
        AssertUtils.isNull(key, "key 不能为空");
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * String转私钥PrivateKey
     *
     * @param key 私钥字符
     */
    public static PrivateKey getPrivateKey(String key) throws Exception
    {
        AssertUtils.isNull(key, "key 不能为空");
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }
}
