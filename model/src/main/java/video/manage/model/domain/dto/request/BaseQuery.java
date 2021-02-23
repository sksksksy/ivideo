package video.manage.model.domain.dto.request;

import lombok.Data;

/**
 * 基础查询
 */
@Data
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
