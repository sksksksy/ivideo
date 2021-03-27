package video.info.service.utils;

import video.info.service.status.QueryConst;
import video.manage.model.dto.request.QueryVideoDto;

/**
 * 该模块下的通用util
 *
 * @author zhp
 */
public class CommonUtilInModule {
    /**
     * 检查query是否为空，若为空表示为游客登录
     *
     * @param query
     * @return
     */
    public static QueryVideoDto checkQueryVideo(QueryVideoDto query) {
        if (query == null) {
            query = new QueryVideoDto();
            query.setPersonId(QueryConst.TOURIST);
        }
        return query;
    }
}
