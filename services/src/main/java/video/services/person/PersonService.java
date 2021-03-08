package video.services.person;

import video.manage.model.dto.request.QueryBaseByKey;
import video.manage.model.entity.PersonInfoEntity;

/**
 * 个人服务
 */
public interface PersonService {
    /**
     * 获取个人信息
     * @param query
     * @return
     */
    PersonInfoEntity getPerson(QueryBaseByKey query);
}
