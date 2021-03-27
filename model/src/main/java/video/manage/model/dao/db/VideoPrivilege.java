package video.manage.model.dao.db;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 视频权限问题
 *
 * @author zhp
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VideoPrivilege implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * video_privilege表id
     */
    private String vpId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 视频id唯一的id
     */
    private String videoId;


}
