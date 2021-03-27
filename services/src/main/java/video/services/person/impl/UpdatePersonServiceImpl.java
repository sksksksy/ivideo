package video.services.person.impl;

import org.springframework.stereotype.Service;
import video.manage.model.dto.request.UpdateUserBaseInfoDto;
import video.manage.model.dto.request.UpdateUserInfoDto;
import video.manage.model.dto.request.UpdateUserLoginDto;
import video.services.person.UpdatePersonService;

@Service
public class UpdatePersonServiceImpl implements UpdatePersonService {
    /**
     * 更新个人基本信息
     *
     * @param baseInfo
     * @return
     */
    @Override
    public UserBaseInfoDao updateOnePersonInfo(UserBaseInfoDao baseInfo) {
        return null;
    }

    /**
     * 更新个人登录信息
     *
     * @param loginDao
     * @return
     */
    @Override
    public UserLoginDao updateOnePersonLogin(UserLoginDao loginDao) {
        return null;
    }

    /**
     * 直接更新复杂的数据
     *
     * @param userInfo@return
     */
    @Override
    public UpdateUserInfoDto updateOnePersonInfo(UpdateUserInfoDto userInfo) {
        UpdateUserLoginDto loginInfo = userInfo.getLoginInfo();
        UpdateUserBaseInfoDto baseInfo = userInfo.getBaseInfo();
        return null;
    }

    /**
     * 删除个人基本信息
     *
     * @param baseInfo
     * @return
     */
    @Override
    public UserBaseInfoDao removeOnePersonInfo(UserBaseInfoDao baseInfo) {
        return null;
    }

    /**
     * 保存个人基本信息
     *
     * @param baseInfo
     * @return
     */
    @Override
    public UserBaseInfoDao saveOnePersonInfo(UserBaseInfoDao baseInfo) {
        return null;
    }

    /**
     * 删除个人登录信息
     *
     * @param loginDao
     * @return
     */
    @Override
    public UserLoginDao removeOnePersonLogin(UserLoginDao loginDao) {
        return null;
    }

    /**
     * 保存个人登录信息
     *
     * @param loginDao
     * @return
     */
    @Override
    public UserLoginDao saveOnePersonLogin(UserLoginDao loginDao) {
        return null;
    }
}
