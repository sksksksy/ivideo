package com.java.tool;

import com.java.exception.BaseRunException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * IO相关工具类
 *
 * @author zhoup
 */
public class IoTools {
    /**
     * 压缩文件
     * @param inputStream 文件输入流
     * @param outputStream 文件输出流
     * @param name 文件名
     */
    public static void compressOfZipForStream(InputStream inputStream, OutputStream outputStream, String name) {
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        try {
            zipOutputStream.putNextEntry(new ZipEntry(name));
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                zipOutputStream.write(buffer, 0, len);
            }
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            BaseRunException.throwException("导出压缩包流失败。", e);
        } finally {
            shutdownOutputStream(zipOutputStream);
        }
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
