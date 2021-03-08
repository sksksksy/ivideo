package video.manage.service.controller.handler;

import com.java.result.ResultS;
import com.java.three.spring.utils.RequestTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import video.manage.model.dto.request.QueryVideo;
import video.manage.model.entity.VideoEntity;
import video.services.video.QueryVideoService;

@Component
public class LoginHandler {
    @Autowired
    QueryVideoService videoService;

    /**
     * 登录请求
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> login(ServerRequest request) {
        QueryVideo query = new QueryVideo();
        RequestTools.getParameter(query, name -> request.queryParam(name).get());
        VideoEntity video = videoService.getVideo(query);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(ResultS.success(video)), ResultS.class);
    }
}
