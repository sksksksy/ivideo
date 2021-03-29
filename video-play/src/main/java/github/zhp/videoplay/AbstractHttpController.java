package github.zhp.videoplay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AbstractHttpController {
    @Autowired(required = false)
    protected HttpServletRequest request;
    @Autowired(required = false)
    protected HttpServletResponse response;
}
