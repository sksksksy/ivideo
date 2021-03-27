package video.manage.model.dao.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 评论信息
 *
 * @author zhp
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReviewInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Long pid;

    /**
     * 评论id
     */
    private String reviewId;

    /**
     * 评论内容
     */
    private String reviewComment;


}
