package com.java.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 自己实现的简单字符转换
 *
 * @author zhoup
 */
public class ISimpleEncrypt {
    char[] s = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k'};
    private final static String DEFAULT_CHARSET = "utf-8";

    private byte[] en(byte[] bytes) {
        byte[] rr = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            int e = bytes[i] & 0xff;
            int a = e >> 4;
            int b = e & 0xf;
            int r = b << 4 | a;
            rr[i] = (byte) r;
        }
        return rr;
    }

    private String ts(byte[] bytes) {
        StringBuffer sr = new StringBuffer(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i] & 0xff;
            char a = s[b % 0x15];
            char k = s[b / 0x15];
            sr.append(a);
            sr.append(k);
        }
        return sr.toString();
    }

    private byte[] tb(String s1) {
        int l = s1.length();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int i = 0;
        while (i < l) {
            int j = i(s1.charAt(i));
            int k = i(s1.charAt(i + 1));
            bos.write(k * 0x15 + j);
            i = i + 2;
        }
        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    /**
     * 加密
     *
     * @param s
     * @return
     */
    public String ens(String s) {
        String r = null;
        try {
            byte[] en = en(s.getBytes(DEFAULT_CHARSET));
            r = ts(en);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * 解密
     *
     * @param s
     * @return
     */
    public String des(String s) {
        byte[] bytes = tb(s);
        return gs(en(bytes));
    }

    private String gs(byte[] bytes) {

        String s = null;
        try {
            s = new String(bytes, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    private int i(char e) {
        int i = -1, l = s.length, j = 0;
        while (j < l) {
            if (s[j] == e) {
                i = j;
                break;
            }
            j++;
        }
        return i;
    }
}
