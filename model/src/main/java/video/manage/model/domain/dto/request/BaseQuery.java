package video.manage.model.domain.dto.request;

/**
 * 基础查询
 */
public class BaseQuery {
    /**
     * 每页大小
     */
    private Integer pageSize;
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 关键字
     */
    private String keyWord;
}
