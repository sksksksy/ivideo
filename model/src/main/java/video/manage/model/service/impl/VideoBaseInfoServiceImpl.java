package video.manage.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import video.manage.model.dao.db.VideoBaseInfo;
import video.manage.model.mapper.VideoBaseInfoMapper;
import video.manage.model.service.IVideoBaseInfoService;

/**
 * 视频基本信息
 *
 * @author zhp
 * @since 2021-03-27
 */
@Service
public class VideoBaseInfoServiceImpl extends ServiceImpl<VideoBaseInfoMapper, VideoBaseInfo> implements IVideoBaseInfoService {

}
