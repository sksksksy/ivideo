package video.services.person;

import video.manage.model.dto.request.UpdateUserInfoDto;

/**
 * 个人信息的更新操作
 */
public interface UpdatePersonService {
    /**
     * 更新个人基本信息
     *
     * @param baseInfo
     * @return
     */
    UserBaseInfoDao updateOnePersonInfo(UserBaseInfoDao baseInfo);

    /**
     * 删除个人基本信息
     *
     * @param baseInfo
     * @return
     */
    UserBaseInfoDao removeOnePersonInfo(UserBaseInfoDao baseInfo);

    /**
     * 保存个人基本信息
     *
     * @param baseInfo
     * @return
     */
    UserBaseInfoDao saveOnePersonInfo(UserBaseInfoDao baseInfo);

    /**
     * 更新个人登录信息
     *
     * @param loginDao
     * @return
     */
    UserLoginDao updateOnePersonLogin(UserLoginDao loginDao);

    /**
     * 删除个人登录信息
     *
     * @param loginDao
     * @return
     */
    UserLoginDao removeOnePersonLogin(UserLoginDao loginDao);

    /**
     * 保存个人登录信息
     *
     * @param loginDao
     * @return
     */
    UserLoginDao saveOnePersonLogin(UserLoginDao loginDao);

    /**
     * 直接更新复杂的数据
     *
     * @param userInfo
     * @return
     */
    UpdateUserInfoDto updateOnePersonInfo(UpdateUserInfoDto userInfo);
}
