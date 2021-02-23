package video.services.video.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public Page<VideoEntity> getAllVideoOfPersonUp(BaseQuery query) {
        return null;
    }
}
