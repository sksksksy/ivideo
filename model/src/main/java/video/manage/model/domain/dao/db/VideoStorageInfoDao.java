package video.manage.model.domain.dao.db;

import lombok.Data;

import java.util.Date;

/**
 * 视频存储信息
 */
@Data
public class VideoStorageInfoDao {
    /**
     *
     */
    private String vsiInfo;
    /**
     * 视频名字
     */
    private String vsiVideoName;
    /**
     * 720p视频存放地址
     */
    private String vsi720pUrl;
    /**
     * 创建时间
     */
    private Date createTime;
}
