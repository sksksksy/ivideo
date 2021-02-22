package video.manage.model.domain.dao.db;

import lombok.Data;

import java.util.Date;

/**
 * 用户登录信息
 */
@Data
public class UserLoginDao {
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
     * 日期
     */
    private Date createTime;
}
