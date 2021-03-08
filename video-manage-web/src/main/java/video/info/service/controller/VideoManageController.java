package video.info.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java.result.ResultS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import video.manage.model.dto.request.QueryVideo;
import video.manage.model.entity.VideoEntity;
import video.services.video.QueryVideoService;

@RestController
public class VideoManageController {
    @Autowired
    QueryVideoService queryVideo;

    /**
     * 查询个人上传的所有视频
     *
     * @return
     */
    @RequestMapping(value = "allVideoOfPersonUp", method = RequestMethod.GET)
    ResultS<Page<VideoEntity>> queryAllVideoOfPersonUp(@RequestBody(required = false) QueryVideo query) {
        Page<VideoEntity> videos = queryVideo.getAllVideoOfPersonUp(query);
        return ResultS.success(videos);
    }

    /**
     * 查询个人能看到的所有视频
     *
     * @return
     */
    @RequestMapping(value = "allVideoOfView", method = RequestMethod.GET)
    ResultS<Page<VideoEntity>> queryAllVideoOfView(@RequestBody(required = false) QueryVideo query) {
        Page<VideoEntity> views = queryVideo.getAllVideoOfView(query);
        return ResultS.success(views);
    }

    /**
     * 搜索视频
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "searchVideoByName", method = RequestMethod.GET)
    ResultS<Page<VideoEntity>> queryVideo(@RequestBody(required = false) QueryVideo query) {
        queryVideo.searchVideoByName(query);
        return ResultS.success();
    }
}
