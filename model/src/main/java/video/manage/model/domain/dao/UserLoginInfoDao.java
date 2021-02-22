package video.manage.model.domain.dao;

import lombok.Data;

/**
 * 用户登录信息
 */
@Data
public class UserLoginInfoDao {
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 用户登录名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String userPassword;
}
