package video.manage.model.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateUserBaseInfoDto implements Serializable {
    static final long serialVersionUID = 1L;
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
