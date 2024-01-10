package com.projects.dath.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ProblemDetail resourceNotFoundHandler(
            ResourceNotFound exception,
            WebRequest request
    ) {
        String msg;
        if (exception instanceof UnknownProduct) {
            msg = "Product";
        } else if (exception instanceof UnknownCategory) {
            msg = "Category";
        } else {
            msg = "Resource";
        }
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        pd.setTitle("Resource not found");
        log.info(request.getContextPath());
        pd.setInstance(URI.create(request.getContextPath()));
        return pd;
    }
}
