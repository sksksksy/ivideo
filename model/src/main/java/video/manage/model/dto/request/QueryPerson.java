package video.manage.model.dto.request;

import lombok.Data;
import video.manage.model.PagingConst;

/**
 * 查询个人信息，用户信息所需参数
 *
 * @author zhoup
 */
@Data
public class QueryPerson {
    /**
     * 每页大小
     */
    private Integer pageSize = PagingConst.DEFAULT_PAGE_SIZE;
    /**
     * 当前页
     */
    private Integer currentPage = PagingConst.DEFAULT_CURRENT_PAGE;
    /**
     * 用户名
     */
    private String chatName;
    /**
     * 用户Id
     */
    private String personId;
}
