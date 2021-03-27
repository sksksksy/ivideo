package video.manage.model.dao.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 视频存储
 *
 * @author zhp
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VideoStorageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * video_storage_info表id
     */
    private String vsiInfo;

    /**
     * 存储的视频名
     */
    private String vsiVideoName;

    /**
     * 视频质量-480p或者720p
     */
    private String videoQuality;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 视频的唯一ID
     */
    private String videoId;

    /**
     * 视频的播放地址
     */
    private String videoUrl;


}
