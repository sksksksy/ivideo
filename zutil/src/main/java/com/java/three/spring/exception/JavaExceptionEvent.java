package com.java.three.spring.exception;

import com.java.exception.BaseRunException;
import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 */
public class JavaExceptionEvent extends ApplicationEvent {
    private BaseRunException exception;

    public JavaExceptionEvent(Object source) {
        super(source);
    }

    public JavaExceptionEvent(Object source, BaseRunException e) {
        super(source);
        this.exception = e;
    }

    public Exception getException() {
        return this.exception;
    }
}
