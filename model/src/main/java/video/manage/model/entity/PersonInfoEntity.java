package video.manage.model.entity;

import lombok.Data;

import java.util.List;

/**
 * 个人信息
 */
@Data
public class PersonInfoEntity {
    /**
     * 用户Id
     */
    private String personId;
    /**
     * 网名
     */
    private String chatName;
    /**
     * 个人介绍
     */
    private String introduce;
    /**
     * 个人视频
     */
    private List<VideoEntity> videos;
    /**
     * 个人发表的评论
     */
    private List<ReviewEntity> reviews;
}
