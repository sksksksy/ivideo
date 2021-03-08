package video.manage.model.dto.response;

import lombok.Data;
import lombok.ToString;
import video.manage.model.dto.VideoBaseInfoDto;

/**
 * 进行删除，更新，插入等操作时，返回的视频信息
 */
@Data
@ToString(callSuper = true)
public class VideoInfoDto extends VideoBaseInfoDto {
    /**
     * 视频权限
     */
    private String privilege;
}
