package video.manage.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import video.manage.model.dao.db.VideoPrivilege;
import video.manage.model.mapper.VideoPrivilegeMapper;
import video.manage.model.service.IVideoPrivilegeService;

/**
 * 视频权限
 *
 * @author zhp
 * @since 2021-03-27
 */
@Service
public class VideoPrivilegeServiceImpl extends ServiceImpl<VideoPrivilegeMapper, VideoPrivilege> implements IVideoPrivilegeService {

}
