package github.zhp.videominio.storage;

import java.io.InputStream;

/**
 * Oss写
 */
public interface OssWriter {
    void putFile(String bucketName, String fileName, InputStream file, long fileSize);
}
