package video.manage.model.dto.request;

import lombok.Data;
import video.manage.model.PagingConst;

/**
 * 视频查询所需参数
 *
 * @author zhoup
 */
@Data
public class QueryVideo {
    /**
     * 每页大小
     */
    private Integer pageSize = PagingConst.DEFAULT_PAGE_SIZE;
    /**
     * 当前页
     */
    private Integer currentPage = PagingConst.DEFAULT_CURRENT_PAGE;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 用户Id
     */
    private String personId;
    /**
     * 视频的url
     */
    private String videoUrl;
}
