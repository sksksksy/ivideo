package video.manage.model.domain.dto;

import lombok.Data;

/**
 * 视频最基本的信息
 */
@Data
public class VideoBaseInfoDto {
    /**
     * 视频的Id
     */
    private String videoId;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频链接
     */
    private String videoUrl;
}
