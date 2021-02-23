package video.manage.service.controller.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import video.manage.service.controller.handler.LoginHandler;

@Configuration
public class URLMapRoute {
    @Autowired
    LoginHandler loginHandler;

    @Bean
    RouterFunction login() {
        return RouterFunctions.route(RequestPredicates.POST("/login"), loginHandler::login);
    }
}
