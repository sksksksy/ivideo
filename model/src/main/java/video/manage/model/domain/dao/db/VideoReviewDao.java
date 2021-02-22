package video.manage.model.domain.dao.db;

import lombok.Data;

import java.util.Date;

/**
 * 视频评论
 */
@Data
public class VideoReviewDao {
    /**
     * 视频评论Id
     */
    private String reviewId;
    /**
     * 创建时间
     */
    private Date createTime;
}
