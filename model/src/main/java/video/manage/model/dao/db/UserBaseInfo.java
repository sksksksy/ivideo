package video.manage.model.dao.db;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户基本信息
 *
 * @author zhp
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String ubId;

    /**
     * 真实姓名
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
     * 对应的u_login表id
     */
    private Integer uId;

    /**
     * 电话号码
     */
    private String ubPhone;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
