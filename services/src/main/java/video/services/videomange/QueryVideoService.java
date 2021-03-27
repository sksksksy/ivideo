package video.services.videomange;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import video.manage.model.dto.request.QueryVideoDto;
import video.manage.model.entity.VideoEntity;

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
    VideoEntity getVideo(QueryVideoDto query);

    /**
     * 查询个人上传的所有视频
     *
     * @param query
     * @return
     */
    Page<VideoEntity> getAllVideoOfPersonUp(QueryVideoDto query);

    /**
     * 查询个人可以看到的所有视频并分页
     *
     * @param query
     * @return
     */
    Page<VideoEntity> getAllVideoOfView(QueryVideoDto query);

    /**
     * 按名字搜索视频
     *
     * @param query
     * @return
     */
    Page<VideoEntity> searchVideoByName(QueryVideoDto query);

    /**
     * 查询视频的播放地址
     *
     * @return
     */
    String queryVideoURLById(QueryVideoDto queryVideoDto);
}
