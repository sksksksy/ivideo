package com.java.string;

import javax.annotation.Nullable;

/**
 * 字符串监测类
 *
 * @author zhoup
 */
public class StringCheck {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || eliminateSpecificSymbol(str).equals("");
    }

    public static boolean nonEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 剔除字符串中的特殊字符，如制表符，回退符，换行符
     *
     * @param str
     * @return
     */
    public static String eliminateSpecificSymbol(@Nullable String str) {
        return str.replaceAll("\\s{1,}|\t|\r|\n", "");
    }

    /**
     * 检测字符类型是否为空
     *
     * @param charSequence
     * @param message
     * @return
     */
    public static CharSequence requireNonEmpty(CharSequence charSequence, String message) {
        if (charSequence == null || charSequence.length() == 0) {
            throw new NullPointerException(message);
        }
        return charSequence;
    }
}
