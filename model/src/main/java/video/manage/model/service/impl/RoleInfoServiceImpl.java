package video.manage.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import video.manage.model.dao.db.RoleInfo;
import video.manage.model.mapper.RoleInfoMapper;
import video.manage.model.service.IRoleInfoService;

/**
 * 角色信息
 *
 * @author zhp
 * @since 2021-03-27
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements IRoleInfoService {

}
