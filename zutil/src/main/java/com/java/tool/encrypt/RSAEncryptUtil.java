package com.java.tool.encrypt;

import lombok.Data;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密工具类
 */
@Data
public class RSAEncryptUtil {
    private String publicKey;
    private String privateKey;
    private byte[] publicKeyBytes;
    private byte[] privateKeyBytes;

    public void genKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //初始化密钥对生成器，密钥大小为96-1024位
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String privateKeyString = EncryptUtils.INSTANCE.DefaultBase64EncodeAsStr(privateKey.getEncoded());
        String publicKeyString = EncryptUtils.INSTANCE.DefaultBase64EncodeAsStr(publicKey.getEncoded());
        this.privateKey = privateKeyString;
        this.publicKey = publicKeyString;
        this.publicKeyBytes = publicKey.getEncoded();
        this.privateKeyBytes = privateKey.getEncoded();
    }

    /**
     * 公钥加密
     *
     * @param str
     * @param publicKeyBytes
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public byte[] encrypt(String str, byte[] publicKeyBytes) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        RSAPublicKey pubkey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubkey);
        byte[] resultByte = cipher.doFinal(str.getBytes(Charset.forName("utf-8")));

        return resultByte;
    }

    public String encryptAsString(String str, byte[] publicKeyBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        byte[] bytes = encrypt(str, publicKeyBytes);
        return byteArrayAsString(bytes);
    }

    /**
     * 私钥解密
     *
     * @param str
     * @param privateKeyBytes
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] decrypt(String str, byte[] privateKeyBytes) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        RSAPrivateKey privkey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new X509EncodedKeySpec(privateKeyBytes));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privkey);
        byte[] resultByte = cipher.doFinal(str.getBytes(Charset.forName("utf-8")));

        return resultByte;
    }

    public String decryptAsString(String str, byte[] privateKeyBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        byte[] bytes = decrypt(str, privateKeyBytes);
        return byteArrayAsString(bytes);
    }

    private String byteArrayAsString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
