package video.services.video.impl;

import org.springframework.stereotype.Service;
import video.manage.model.domain.dto.VideoBaseInfoDto;
import video.manage.model.domain.dto.response.VideoInfoDto;
import video.services.video.UpdateVideoService;

import java.util.List;

/**
 * 视频更新操作
 */
@Service
public class UpdateVideoServiceImpl implements UpdateVideoService {
    /**
     * 保存一个视频信息
     *
     * @param videoInfoDto
     * @return
     */
    @Override
    public VideoBaseInfoDto saveOneOfVideo(VideoInfoDto videoInfoDto) {
        return null;
    }

    /**
     * 批量保存数据
     *
     * @param videoInfoDtoS
     * @return
     */
    @Override
    public VideoBaseInfoDto saveBatchOfVideo(List<VideoInfoDto> videoInfoDtoS) {
        return null;
    }

    /**
     * 更新一条视频信息
     *
     * @param videoInfoDto
     * @return
     */
    @Override
    public VideoBaseInfoDto updateOneOfVideo(VideoInfoDto videoInfoDto) {
        return null;
    }

    /**
     * 批量更新视频信息
     *
     * @param videoInfoDto
     * @return
     */
    @Override
    public VideoBaseInfoDto updateBatchOfVideo(List<VideoInfoDto> videoInfoDto) {
        return null;
    }

    /**
     * 删除一条视频信息
     *
     * @param videoInfoDto
     * @return
     */
    @Override
    public VideoBaseInfoDto removeOneOfVideo(VideoInfoDto videoInfoDto) {
        return null;
    }

    /**
     * 批量删除视频信息
     *
     * @param videoInfoDto
     * @return
     */
    @Override
    public VideoBaseInfoDto removeBatchOfVideo(List<VideoInfoDto> videoInfoDto) {
        return null;
    }
}
