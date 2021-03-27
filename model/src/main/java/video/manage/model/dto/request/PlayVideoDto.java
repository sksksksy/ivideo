package video.manage.model.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 视频播放的请求类
 */
@Data
public class PlayVideoDto implements Serializable {
    static final long serialVersionUID = 1L;
    /**
     * 偏移量，即当前的开始位置
     */
    private Long offset = 0L;
    /**
     * 偏移长度
     */
    private Long length;

    /**
     * videoId
     */
    private String videoId;

    /**
     * 下一偏移量=偏移量+偏移长度+1
     */
    public Long getNextOffset() {
        return offset + length + 1;
    }
}
