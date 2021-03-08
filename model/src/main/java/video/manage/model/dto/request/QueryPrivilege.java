package video.manage.model.dto.request;

import lombok.Data;
import video.manage.model.PagingConst;

/**
 * 查询权限所需参数
 *
 * @author zhoup
 */
@Data
public class QueryPrivilege {
    /**
     * 每页大小
     */
    private Integer pageSize = PagingConst.DEFAULT_PAGE_SIZE;
    /**
     * 当前页
     */
    private Integer currentPage = PagingConst.DEFAULT_CURRENT_PAGE;
    /**
     * 通用id
     */
    private String usuallyId;
    /**
     * 与权限匹配的对象Id
     */
    private String matchObject;
    /**
     * p为查询人的权限，v为查询视频的权限
     * p-usuallyId  userId
     * v-usuallyId  videoId
     */
    private String type;
}
