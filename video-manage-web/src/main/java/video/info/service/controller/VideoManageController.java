package video.info.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java.result.ResultS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import video.manage.model.domain.dto.request.BaseQuery;
import video.manage.model.domain.entity.VideoEntity;
import video.services.video.VideoService;

@RestController
public class VideoManageController {
    @Autowired
    VideoService videoService;

    /**
     * 查询个人上传的所有视频
     *
     * @return
     */
    @RequestMapping(value = "allVideoOfPersonUp", method = RequestMethod.GET)
    ResultS<Page<VideoEntity>> queryAllVideoOfPersonUp(@RequestBody(required = false) BaseQuery query) {
        Page<VideoEntity> videos = videoService.getAllVideoOfPersonUp(query);
        return ResultS.success(videos);
    }

    /**
     * 查询个人能看到的所有视频
     *
     * @return
     */
    @RequestMapping(value = "allVideoOfView", method = RequestMethod.GET)
    ResultS<Page<VideoEntity>> queryAllVideoOfView(@RequestBody(required = false) BaseQuery query) {
        Page<VideoEntity> views = videoService.getAllVideoOfView(query);
        return ResultS.success(views);
    }
}
