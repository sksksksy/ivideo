package video.manage.model.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateUserLoginDto implements Serializable {
    static final long serialVersionUID = 1L;
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
