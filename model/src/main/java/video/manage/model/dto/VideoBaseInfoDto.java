package video.manage.model.dto;

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
     * 视频的名字，不同质量的视频拥有相同的videoFlag
     */
    private String videoFlag;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频链接
     */
    private String videoUrl;
    /**
     * 视频的质量
     */
    private String videoQuality;
}
