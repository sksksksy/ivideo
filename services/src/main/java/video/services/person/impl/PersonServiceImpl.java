package video.services.person.impl;

import org.springframework.stereotype.Service;
import video.manage.model.domain.dto.request.BaseQuery;
import video.manage.model.domain.entity.PersonInfoEntity;
import video.services.person.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public PersonInfoEntity getPerson(BaseQuery query) {
        return null;
    }
}
