package video.manage.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import video.manage.model.dao.db.VideoReview;
import video.manage.model.mapper.VideoReviewMapper;
import video.manage.model.service.IVideoReviewService;

/**
 * 视频评论
 *
 * @author zhp
 * @since 2021-03-27
 */
@Service
public class VideoReviewServiceImpl extends ServiceImpl<VideoReviewMapper, VideoReview> implements IVideoReviewService {

}
