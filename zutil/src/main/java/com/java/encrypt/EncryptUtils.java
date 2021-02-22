package com.java.encrypt;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

public class EncryptUtils {
    public final static EncryptUtils INSTANCE = new EncryptUtils();
    /**
     * md5算法
     */
    public static final String ALGORITHM_MD5 = "MD5";
    /**
     * sha-1算法
     */
    public static final String ALGORITHM_SHA_1 = "SHA-1";
    /**
     * base64加密器
     */
    private final static Base64.Encoder encoder = Base64.getEncoder();
    /**
     * base64解密器
     */
    private final static Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 根据encryptType加密算法获取byte
     *
     * @param input
     * @param encryptType
     * @return
     */
    public byte[] encryptToByte(byte[] input, String encryptType) {
        MessageDigest instance = null;
        try {
            instance = MessageDigest.getInstance(encryptType);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(encryptType + ":NoSuchAlgorithmException", e);
        }
        byte[] digest = instance.digest(input);
        return digest;
    }

    /**
     * 根据encryptType加密算法获取加密后的16进制字符
     *
     * @param input
     * @param encryptType
     * @return
     */
    public String encryptAsHexString(byte[] input, String encryptType) {
        StringBuffer result = new StringBuffer();
        byte[] digest = encryptToByte(input, encryptType);
        for (byte b : digest) {
            int c = b & 0xff;
            result.append(Integer.toHexString(c));
        }
        return result.toString();
    }

    public String toMd5AsHexString(String message) {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        return encryptAsHexString(bytes, ALGORITHM_MD5);
    }

    public String toSha1AsHexString(String message) {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        return encryptAsHexString(bytes, ALGORITHM_SHA_1);
    }

    /**
     * 默认的编码加密
     *
     * @param message
     * @return
     */
    public String DefaultBase64EncodeAsStr(String message) {
        return DefaultBase64EncodeAsStr(message.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 默认编码解密
     *
     * @param encodeStr
     * @return
     */
    public String DefaultBase64DecodeAsStr(String encodeStr) {
        return base64DecodeAsByte(encodeStr, StandardCharsets.ISO_8859_1, StandardCharsets.UTF_8);
    }

    /**
     * 默认编码加密
     *
     * @param bytes
     * @return
     */
    public String DefaultBase64EncodeAsStr(byte[] bytes) {
//        byte[] encoded = base64Encode(bytes);
//        return new String(encoded, 0, 0, encoded.length);
        return encoder.encodeToString(bytes);//StandardCharsets.ISO_8859_1
    }

    public byte[] base64Encode(byte[] bytes) {
        return encoder.encode(bytes);
    }

    /**
     * @param encodedStr
     * @param charset
     * @return
     */
    public byte[] base64DecodeAsByte(String encodedStr, Charset charset) {
        byte[] decoded = null;
        if (Objects.isNull(charset)) {
            decoded = decoder.decode(encodedStr);
        } else {
            byte[] bytes = encodedStr.getBytes(charset);
            decoded = decoder.decode(bytes);
        }
        return decoded;
    }

    /**
     * base64加密
     *
     * @param encodeStr
     * @param charset
     * @param strCharset
     * @return
     */
    public String base64DecodeAsByte(String encodeStr, Charset charset, Charset strCharset) {
        byte[] bytes = base64DecodeAsByte(encodeStr, charset);
        return new String(bytes, strCharset);
    }


}
