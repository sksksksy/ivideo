package video.manage.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import video.services.video.QueryVideoService;


@Service
public class LoginService {
    @Autowired
    QueryVideoService videoService;

}
