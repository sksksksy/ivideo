package video.manage.model.dao.db;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户登录信息
 *
 * @author zhp
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String uId;

    /**
     * 用户名
     */
    private String uName;

    /**
     * 用户密码
     */
    private String uPassword;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
