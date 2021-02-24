package video.services.video.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import video.manage.model.domain.dto.request.BaseQuery;
import video.manage.model.domain.entity.VideoEntity;
import video.services.video.QueryVideoService;

/**
 * 视频操作服务类
 */
@Service
public class QueryVideoServiceImpl implements QueryVideoService {
    /**
     * 获取单个基本视频信息
     *
     * @param query
     * @return
     */
    @Override
    public VideoEntity getVideo(BaseQuery query) {
        return null;
    }

    /**
     * 查询个人上传的所有视频
     *
     * @param query
     * @return
     */
    @Override
    public Page<VideoEntity> getAllVideoOfPersonUp(BaseQuery query) {
        return null;
    }

    /**
     * 查询个人可以看到的所有视频并分页
     *
     * @param query
     * @return
     */
    @Override
    public Page<VideoEntity> getAllVideoOfView(BaseQuery query) {
        return null;
    }

    /**
     * 按名字搜索视频
     *
     * @param query
     * @return
     */
    @Override
    public Page<VideoEntity> searchVideoByName(BaseQuery query) {
        return null;
    }
}
