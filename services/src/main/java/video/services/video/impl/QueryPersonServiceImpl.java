package video.services.video.impl;

import org.springframework.stereotype.Service;
import video.manage.model.dto.request.QueryBaseByKey;
import video.manage.model.entity.PersonInfoEntity;
import video.services.video.QueryPersonService;
@Service
public class QueryPersonServiceImpl implements QueryPersonService {
    /**
     * 查询个人的基本信息
     *
     * @param query
     * @return
     */
    @Override
    public PersonInfoEntity queryPersonInformation(QueryBaseByKey query) {
        return null;
    }
}
