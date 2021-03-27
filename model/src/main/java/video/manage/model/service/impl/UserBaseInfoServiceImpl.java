package video.manage.model.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import video.manage.model.dao.db.UserBaseInfo;
import video.manage.model.mapper.UserBaseInfoMapper;
import video.manage.model.service.IUserBaseInfoService;

/**
 * 用户基本信息
 *
 * @author zhp
 * @since 2021-03-27
 */
@Service
public class UserBaseInfoServiceImpl extends ServiceImpl<UserBaseInfoMapper, UserBaseInfo> implements IUserBaseInfoService {

}
