package github.zhp.videoplay.vod;

import github.zhp.core.result.ResultS;
import github.zhp.core.tool.ClassTools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import video.manage.model.dto.request.PlayVideoDto;
import video.manage.model.dto.request.QueryVideoDto;

/**
 * 视频播放
 */
@RequestMapping(path = "video")
@Controller
public class VideoPlayController {
    /**
     * 获取点播视频链接
     *
     * @return
     */
    @GetMapping("/get/url")
    ResultS<String> getVodPlayUrl(@RequestBody QueryVideoDto queryVideo) {

        return ResultS.success();
    }

    void playVideo(@RequestBody(required = false) PlayVideoDto playVideo) {
        playVideo = ClassTools.CheckNullAndCreate(playVideo, PlayVideoDto.class);
    }
}
