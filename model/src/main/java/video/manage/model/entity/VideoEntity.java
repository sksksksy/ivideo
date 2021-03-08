package video.manage.model.entity;

import lombok.Data;

import java.util.List;

/**
 * 存储视频对象信息
 */
@Data
public class VideoEntity {
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频链接
     */
    private String videoUrl;
    /**
     * 针对视频的评论
     */
    private List<ReviewEntity> reviews;
}
