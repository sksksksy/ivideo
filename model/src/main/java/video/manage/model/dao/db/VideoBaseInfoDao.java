package video.manage.model.dao.db;

import lombok.Data;

import java.util.Date;

/**
 * 视频基本信息
 */
@Data
public class VideoBaseInfoDao {
    /**
     * 表Id
     */
    private String vbiId;
    /**
     * 创建时间
     */
    private Date createTime;
}
