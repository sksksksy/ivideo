package video.services.person;

import video.manage.model.dto.request.QueryBaseByKeyDto;
import video.manage.model.entity.PersonInfoEntity;

/**
 * 个人信息的查询操作
 */
public interface QueryPersonService {
    /**
     * 查询个人的基本信息
     *
     * @return
     */
    PersonInfoEntity queryPersonInformation(QueryBaseByKeyDto query);
}
