package github.zhp.videominio.util;

import github.zhp.core.exception.BaseRunException;
import github.zhp.videominio.config.OssSetting;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;

/**
 * MinIo帮助类
 */
public class MinIoHelper {
    /**
     * 获取连接
     *
     * @param setting
     * @return
     */
    public static MinioClient getClient(OssSetting setting) {
        try {
            MinioClient client = new MinioClient(setting.getEndpoint(), setting.getAccessKey(), setting.getSecret());
            return client;
        } catch (InvalidEndpointException e) {
            BaseRunException.throwException("MinIo连接异常", e);
        } catch (InvalidPortException e) {
            BaseRunException.throwException("MinIo连接异常", e);
        }
        return null;
    }
}
