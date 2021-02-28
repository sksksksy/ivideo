package com.java.spring.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class SendExceptionListener implements ApplicationListener<JavaExceptionEvent> {
    @Override
    public void onApplicationEvent(JavaExceptionEvent javaExceptionEvent) {
        Exception exception = javaExceptionEvent.getException();
        if (Objects.nonNull(exception)) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream stream = new PrintStream(outputStream);
            exception.printStackTrace(stream);
            byte[] bytes = outputStream.toByteArray();
            String exceptionStr = new String(bytes, StandardCharsets.UTF_8);
            log.info(exceptionStr);
        }
        log.info("send exception message.");
    }
}
