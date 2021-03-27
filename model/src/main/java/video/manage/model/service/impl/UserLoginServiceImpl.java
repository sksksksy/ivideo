package video.manage.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import video.manage.model.dao.db.UserLogin;
import video.manage.model.mapper.UserLoginMapper;
import video.manage.model.service.IUserLoginService;

/**
 * 用户登录
 *
 * @author zhp
 * @since 2021-03-27
 */
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements IUserLoginService {

}
