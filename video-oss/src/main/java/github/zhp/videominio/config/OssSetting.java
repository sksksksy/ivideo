package github.zhp.videominio.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * minIo相关设置
 */
@Component
@Data
public class OssSetting {
    private String bucketName;
    private String accessKey;
    private String secret;
    private String endpoint;
}
