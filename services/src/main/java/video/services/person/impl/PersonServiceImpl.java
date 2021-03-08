package video.services.person.impl;

import org.springframework.stereotype.Service;
import video.manage.model.dto.request.QueryBaseByKey;
import video.manage.model.entity.PersonInfoEntity;
import video.services.person.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public PersonInfoEntity getPerson(QueryBaseByKey query) {
        return null;
    }
}
