package video.manage.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * 评论
 */
@Data
public class ReviewEntity {
    /**
     * 评论Id
     */
    private String reviewId;
    /**
     * 评论内容
     */
    private String reviewContent;
    /**
     * 评论人
     */
    private String author;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 点赞次数
     */
    private Integer star;
}
