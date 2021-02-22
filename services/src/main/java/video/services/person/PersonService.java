package video.services.person;

import video.manage.model.domain.dto.request.BaseQuery;
import video.manage.model.domain.entity.PersonInfoEntity;

/**
 * 个人服务
 */
public interface PersonService {
    /**
     * 获取个人信息
     * @param query
     * @return
     */
    PersonInfoEntity getPerson(BaseQuery query);
}
