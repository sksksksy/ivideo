package video.services.video;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import video.manage.model.domain.dto.request.BaseQuery;
import video.manage.model.domain.entity.VideoEntity;

/**
 * 查询视频服务类
 */
public interface QueryVideoService {
    /**
     * 获取单个基本视频信息
     *
     * @param query
     * @return
     */
    VideoEntity getVideo(BaseQuery query);

    /**
     * 查询个人上传的所有视频
     *
     * @param query
     * @return
     */
    Page<VideoEntity> getAllVideoOfPersonUp(BaseQuery query);

    /**
     * 查询个人可以看到的所有视频并分页
     *
     * @param query
     * @return
     */
    Page<VideoEntity> getAllVideoOfView(BaseQuery query);

    /**
     * 按名字搜索视频
     *
     * @param query
     * @return
     */
    Page<VideoEntity> searchVideoByName(BaseQuery query);
}
