package video.manage.model.dao.db;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 视频基本信息
 *
 * @author zhp
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VideoBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * video_base_info表id
     */
    private String vbiId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 视频的唯一ID
     */
    private String videoId;

    /**
     * 视频的名字
     */
    private String videoName;

    /**
     * 属于同一个视频的标志
     */
    private String videoFlag;


}
