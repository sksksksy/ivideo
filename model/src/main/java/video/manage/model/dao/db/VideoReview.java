package video.manage.model.dao.db;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 视频评论
 *
 * @author zhp
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VideoReview implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论表id
     */
    private String reviewId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 视频ID
     */
    private String videoId;

    /**
     * 用户id
     */
    private String userId;


}
