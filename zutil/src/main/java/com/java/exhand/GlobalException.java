package com.java.exhand;

import com.java.exception.BaseRunException;
import com.java.result.ResultS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalException implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    @ExceptionHandler(value = BaseRunException.class)
    public ResultS baseRunException(BaseRunException exception, HttpServletResponse response) {
        printStackTrace(exception);
        notifyConsumer(exception);
        return ResultS.fail(exception.getCode(), exception.getMessage(), Collections.EMPTY_LIST);
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    void notifyConsumer(BaseRunException e) {
        Objects.requireNonNull(publisher, "ApplicationEventPublisher can't null.");
        publisher.publishEvent(new JavaExceptionEvent(this, e));
    }

    protected void printStackTrace(Exception e) {
        log.error("程序出错", e);
    }
}
