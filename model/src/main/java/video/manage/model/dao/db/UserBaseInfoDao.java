package video.manage.model.dao.db;

import lombok.Data;

import java.util.Date;

/**
 * 用户基本信息
 */
@Data
public class UserBaseInfoDao {
    /**
     * 表id
     */
    private String ubId;
    /**
     * 名字
     */
    private String ubName;
    /**
     * 地址
     */
    private String ubAddress;
    /**
     * 邮箱
     */
    private String ubEmail;
    /**
     * 用户id
     */
    private String uId;
    /**
     * 电话号码
     */
    private String ubPhone;
    /**
     * 创建时间
     */
    private Date createTime;
}
