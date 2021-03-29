package github.zhp.videominio.read;

import github.zhp.core.tool.ByteTools;
import github.zhp.videominio.config.OssSetting;
import github.zhp.videominio.util.MinIoHelper;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 从minio读数据
 */
@Component
public class MinioReader implements OssReader {
    OssSetting setting;
    //当该Component常驻内存时，不需要重复new一个新的client,有助于提升性能
    //线程安全
    ThreadLocal<MinioClient> client = new ThreadLocal<>();

    @Autowired
    MinioReader(OssSetting set) {
//        client.set(MinIoHelper.getClient(set));
        this.setting = setting;
    }

    /**
     * 从bucketName中读取objectName这个对象
     *
     * @param objectName
     * @return
     */
    //   @SneakyThrows会将throwable强制转为RuntimeException
    @SneakyThrows
    @Override
    public byte[] readObject(String objectName) {
        InputStream object = client.get().getObject(setting.getBucketName(), objectName);
        return ByteTools.StreamToByte(object);
    }

    /**
     * 读取桶中的部分数据
     *
     * @param objectName
     * @param offset
     * @param length
     * @return
     */
    @SneakyThrows
    @Override
    public byte[] readObject(String objectName, Long offset, Long length) {
        InputStream ins = client.get().getObject(setting.getBucketName(), objectName, offset, length);
        return ByteTools.StreamToByte(ins);
    }
}
