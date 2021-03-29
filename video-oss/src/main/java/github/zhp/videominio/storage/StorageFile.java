package github.zhp.videominio.storage;

import github.zhp.core.exception.BaseRunException;
import github.zhp.videominio.config.OssSetting;
import github.zhp.videominio.util.MinIoHelper;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 存储文件
 */
@Component
public class StorageFile implements OssWriter {

    private OssSetting minIoSetting;

    @Autowired
    StorageFile(OssSetting ossSetting) {
        this.minIoSetting = ossSetting;
    }

    /**
     * 上传文件至oss
     *
     * @param bucketName
     * @param fileName
     * @param file
     * @param fileSize
     */
    @Override
    public void putFile(String bucketName, String fileName, InputStream file, long fileSize) {
        MinioClient client = MinIoHelper.getClient(minIoSetting);
        PutObjectOptions options = new PutObjectOptions(fileSize, 0);
        try {
            client.putObject(bucketName, fileName, file, options);
        } catch (Exception e) {
            BaseRunException.throwException("上传文件到MinIo出现异常", e);
        }
    }
}
