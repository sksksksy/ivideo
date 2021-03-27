package video.services.videoplay;

import video.manage.model.dto.request.PlayVideoDto;

/**
 * 视频播放
 */
public interface PlayVideoService {
    /**
     * 视频播放
     *
     * @param playVideoDto
     */
    void playVideo(PlayVideoDto playVideoDto);
}
