package video.services.video;

import video.manage.model.dto.request.QueryBaseByKey;
import video.manage.model.entity.PersonInfoEntity;

/**
 * 个人信息的查询操作
 */
public interface QueryPersonService {
    /**
     * 查询个人的基本信息
     * @return
     */
    PersonInfoEntity queryPersonInformation(QueryBaseByKey query);
}
