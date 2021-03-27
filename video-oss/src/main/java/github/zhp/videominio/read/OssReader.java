package github.zhp.videominio.read;

import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * oss读
 */
public interface OssReader {
    /**
     * 从bucketName中读取objectName这个对象
     *
     * @param objectName
     * @return
     */
    byte[] readObject(String objectName) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException;

    /**
     * 读取桶中的部分数据
     *
     * @param objectName
     * @param offset
     * @param length
     * @return
     */
    byte[] readObject(String objectName, Long offset, Long length);
}
