package video.services.video.impl;

import org.springframework.stereotype.Service;
import video.manage.model.domain.dto.request.BaseQuery;
import video.manage.model.domain.entity.VideoEntity;
import video.services.video.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
    @Override
    public VideoEntity getVideo(BaseQuery query) {
        return null;
    }
}
