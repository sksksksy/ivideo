package video.services.videomange;

import video.manage.model.dto.VideoBaseInfoDto;
import video.manage.model.dto.response.VideoInfoDto;

import java.util.List;

/**
 * 更新视频服务
 * 增，删，改
 *
 * @author zhp
 */
public interface UpdateVideoService {
    /**
     * 保存一个视频信息
     *
     * @param videoInfoDto
     * @return
     */
    VideoBaseInfoDto saveOneOfVideo(VideoInfoDto videoInfoDto);

    /**
     * 批量保存数据
     *
     * @param videoInfoDtoS
     * @return
     */
    VideoBaseInfoDto saveBatchOfVideo(List<VideoInfoDto> videoInfoDtoS);

    /**
     * 更新一条视频信息
     *
     * @param videoInfoDto
     * @return
     */
    VideoBaseInfoDto updateOneOfVideo(VideoInfoDto videoInfoDto);

    /**
     * 批量更新视频信息
     *
     * @param videoInfoDto
     * @return
     */
    VideoBaseInfoDto updateBatchOfVideo(List<VideoInfoDto> videoInfoDto);

    /**
     * 删除一条视频信息
     *
     * @param videoInfoDto
     * @return
     */
    VideoBaseInfoDto removeOneOfVideo(VideoInfoDto videoInfoDto);

    /**
     * 批量删除视频信息
     *
     * @param videoInfoDto
     * @return
     */
    VideoBaseInfoDto removeBatchOfVideo(List<VideoInfoDto> videoInfoDto);

}
