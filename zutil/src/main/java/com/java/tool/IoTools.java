package com.java.tool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * IO相关工具类
 *
 * @author zhoup
 */
public class IoTools {
    public static void compressOfZipForStream(Iterable<InputStream> ins, List<String> names, OutputStream out) {
        ZipOutputStream zipOutputStream = new ZipOutputStream(out);
//        ins.forEach();
    }

    /**
     * 关闭输出流
     *
     * @param outputStream
     */
    public static void shutdownOutputStream(OutputStream outputStream) {
        if (null != outputStream) {
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("关闭outputStream失败");
            }
        }
    }

    /**
     * 关闭输入流
     *
     * @param inputStream
     */
    public static void shutdownInputStream(InputStream inputStream) {
        if (null != inputStream) {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("关闭inputStream失败");
            }
        }
    }
}
