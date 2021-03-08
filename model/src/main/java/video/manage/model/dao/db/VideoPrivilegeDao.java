package video.manage.model.dao.db;

import lombok.Data;

import java.util.Date;

/**
 * 视频权限
 */
@Data
public class VideoPrivilegeDao {
    /**
     * 视频id
     */
    private String vpId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 视频权限
     */
    private String privilege;
}
