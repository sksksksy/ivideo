package video.services.videoplay.impl;

import com.xiaoleilu.hutool.db.nosql.redis.RedisDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import video.manage.model.dto.request.PlayVideoDto;
import video.manage.model.dto.request.QueryVideoDto;
import video.services.videomange.QueryVideoService;
import video.services.videoplay.PlayVideoService;

@Service
public class PlayVideoServiceImpl implements PlayVideoService {

    @Autowired
    QueryVideoService queryVideoService;

    /**
     * 视频播放
     *
     * @param playVideoDto
     */
    @Override
    public void playVideo(PlayVideoDto playVideoDto) {
        RedisDS redisDS = RedisDS.create();
        Jedis jedis = redisDS.getJedis();
        if (jedis.exists(playVideoDto.getVideoId())) {
            //根据URL去那视频流
            String url = jedis.get(playVideoDto.getVideoId());

        } else {
            SetParams params = new SetParams();
            params.ex(60 * 180);//三个小时
            String url = queryVideoService.queryVideoURLById(new QueryVideoDto());
            jedis.set(playVideoDto.getVideoId(), url, params);
        }
    }
}
