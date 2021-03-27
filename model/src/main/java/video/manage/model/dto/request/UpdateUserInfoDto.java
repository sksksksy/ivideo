package video.manage.model.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserInfoDto implements Serializable {
    static final long serialVersionUID = 1L;
    /**
     * 用户基本信息
     */
    UpdateUserBaseInfoDto baseInfo;
    /**
     * 用户登录信息
     */
    UpdateUserLoginDto loginInfo;
    /**
     * 操作类型
     * 1-新增
     * 3-更新
     */
    String operationType;
}
