package video.manage.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import video.manage.model.dao.db.ReviewInfo;
import video.manage.model.mapper.ReviewInfoMapper;
import video.manage.model.service.IReviewInfoService;

/**
 * 评论信息
 *
 * @author zhp
 * @since 2021-03-27
 */
@Service
public class ReviewInfoServiceImpl extends ServiceImpl<ReviewInfoMapper, ReviewInfo> implements IReviewInfoService {

}
