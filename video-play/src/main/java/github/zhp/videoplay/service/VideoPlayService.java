package github.zhp.videoplay.service;

import video.manage.model.dto.request.PlayVideoDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视频播放服务
 */
public interface VideoPlayService {
    /**
     * 转为videoStream传给前端
     *
     * @param playVideo
     */
    void videoStream(PlayVideoDto playVideo, HttpServletRequest request, HttpServletResponse response);
}
