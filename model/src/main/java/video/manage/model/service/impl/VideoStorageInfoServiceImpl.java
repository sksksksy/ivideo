package video.manage.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import video.manage.model.dao.db.VideoStorageInfo;
import video.manage.model.mapper.VideoStorageInfoMapper;
import video.manage.model.service.IVideoStorageInfoService;

/**
 * 视频存储
 *
 * @author zhp
 * @since 2021-03-27
 */
@Service
public class VideoStorageInfoServiceImpl extends ServiceImpl<VideoStorageInfoMapper, VideoStorageInfo> implements IVideoStorageInfoService {

}
