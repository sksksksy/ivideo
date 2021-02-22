package video.manage.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import video.services.video.VideoService;


@Service
public class LoginService {
    @Autowired
    VideoService videoService;

}
