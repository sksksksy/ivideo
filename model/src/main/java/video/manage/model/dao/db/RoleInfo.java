package video.manage.model.dao.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色信息
 *
 * @author zhp
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createAuthor;


}
