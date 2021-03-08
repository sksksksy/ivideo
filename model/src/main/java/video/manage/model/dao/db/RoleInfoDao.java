package video.manage.model.dao.db;

import lombok.Data;

import java.util.Date;

/**
 * 角色基本信息
 */
@Data
public class RoleInfoDao {
    /**
     * 角色Id
     */
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createAuthor;
}
