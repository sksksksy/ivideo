package github.zhp.videoplay.service.impl;

import github.zhp.core.exception.BaseRunException;
import github.zhp.videominio.read.OssReader;
import github.zhp.videoplay.service.VideoPlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import video.manage.model.dto.request.PlayVideoDto;

import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 视频播放服务
 */
@Service
public class VideoPlayServiceImpl implements VideoPlayService {
    @Autowired(required = false)
    OssReader reader;

    /**
     * 转为videoStream传给前端
     *
     * @param playVideo
     * @param request
     * @param response
     */
    @Override
    public void videoStream(PlayVideoDto playVideo, HttpServletRequest request, HttpServletResponse response) {
        byte[] bytes = reader.readObject(playVideo.getVideoId(), playVideo.getOffset(), playVideo.getLength());
        try {
            response.getOutputStream().setWriteListener(new WriteListener() {
                @Override
                public void onWritePossible() throws IOException {
                }

                @Override
                public void onError(Throwable throwable) {
                    BaseRunException.throwException("写入响应流错误");
                }
            });
            response.getOutputStream().write(bytes);
//          返回类型
            response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//          返回的片段
            response.addHeader(HttpHeaders.CONTENT_RANGE, "bytes " + playVideo.getOffset() + "-" + playVideo.getNextOffset() + "/" + playVideo.getLength());
//          返回的长度
            response.addHeader(HttpHeaders.CONTENT_LENGTH, playVideo.getLength().toString());
//          referer字段
            response.addHeader(HttpHeaders.REFERER, request.getRequestURL().toString());
        } catch (IOException e) {
            BaseRunException.throwException("往响应流写数据异常", e);
        }
    }
}
