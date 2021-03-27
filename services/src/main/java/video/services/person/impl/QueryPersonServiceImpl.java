package video.services.person.impl;

import org.springframework.stereotype.Service;
import video.manage.model.dto.request.QueryBaseByKeyDto;
import video.manage.model.entity.PersonInfoEntity;
import video.services.person.QueryPersonService;

@Service
public class QueryPersonServiceImpl implements QueryPersonService {
    /**
     * 查询个人的基本信息
     *
     * @param query
     * @return
     */
    @Override
    public PersonInfoEntity queryPersonInformation(QueryBaseByKeyDto query) {
        return null;
    }
}
