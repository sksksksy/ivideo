package video.info.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import github.zhp.core.result.ResultS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import video.info.service.utils.CommonUtilInModule;
import video.manage.model.dto.request.QueryVideoDto;
import video.manage.model.entity.VideoEntity;
import video.services.videomange.QueryVideoService;

/**
 * 视频管理控制类
 */
@RestController
public class VideoManageController {
    QueryVideoService queryVideo;

    @Autowired(required = false)
    public void setQueryVideo(QueryVideoService queryVideo) {
        this.queryVideo = queryVideo;
    }

    /**
     * 查询个人上传的所有视频
     *
     * @return
     */
    @RequestMapping(value = "allVideoOfPersonUp", method = RequestMethod.GET)
    ResultS<Page<VideoEntity>> queryAllVideoOfPersonUp(@RequestBody(required = false) QueryVideoDto query) {
        CommonUtilInModule.checkQueryVideo(query);
        Page<VideoEntity> videos = queryVideo.getAllVideoOfPersonUp(query);
        return ResultS.success(videos);
    }

    /**
     * 查询个人能看到的所有视频
     *
     * @return
     */
    @RequestMapping(value = "allVideoOfView", method = RequestMethod.GET)
    ResultS<Page<VideoEntity>> queryAllVideoOfView(@RequestBody(required = false) QueryVideoDto query) {
        CommonUtilInModule.checkQueryVideo(query);
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
    ResultS<Page<VideoEntity>> queryVideo(@RequestBody(required = false) QueryVideoDto query) {
        CommonUtilInModule.checkQueryVideo(query);
        queryVideo.searchVideoByName(query);
        return ResultS.success();
    }
}
