package video.info.service.controller;

import github.zhp.core.result.ResultS;
import github.zhp.spring.three.spring.utils.RequestTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.Collection;

@RestController
public class VideoUploadController {
    /**
     * 普通上传视频
     *
     * @param request
     * @return
     */
    @RequestMapping("upload")
    public ResultS<String> uploadVideo(HttpServletRequest request) {
        Collection<Part> parts = RequestTools.getParts(request);
        parts.stream().map(p -> {
            return null;
        });
        return ResultS.success();
    }
}
