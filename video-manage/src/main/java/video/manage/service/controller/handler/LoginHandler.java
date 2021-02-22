package video.manage.service.controller.handler;

import com.java.result.ResultS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;
import video.services.video.VideoService;

@Component
public class LoginHandler {
    @Autowired
    VideoService videoService;

    public Mono<ResultS> login(ServerRequest request) {
        return null;
    }
}
