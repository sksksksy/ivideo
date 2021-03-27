package video.manage.model.dto.request;

import lombok.Data;
import video.manage.model.PagingConst;

import java.io.Serializable;

/**
 * 基础查询,针对只有一个入参的查询
 */
@Data
public class QueryBaseByKeyDto implements Serializable {
    static final long serialVersionUID = 1L;
    /**
     * 每页大小
     */
    private Integer pageSize = PagingConst.DEFAULT_PAGE_SIZE;
    /**
     * 当前页
     */
    private Integer currentPage = PagingConst.DEFAULT_CURRENT_PAGE;
    /**
     * 关键字
     */
    private String keyWord;
}
