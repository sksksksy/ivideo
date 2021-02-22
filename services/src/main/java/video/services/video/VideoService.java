package video.services.video;

import video.manage.model.domain.dto.request.BaseQuery;
import video.manage.model.domain.entity.VideoEntity;

/**
 * 视频服务类
 */
public interface VideoService {
    /**
     * 获取单个基本视频信息
     * @param query
     * @return
     */
    VideoEntity getVideo(BaseQuery query);
}
