package com.tinqinacademy.redis.core.processors;


import com.tinqinacademy.redis.api.errors.Error;
import com.tinqinacademy.redis.api.errors.ErrorOutput;
import io.vavr.API;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;

import java.util.List;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Predicates.instanceOf;

@RequiredArgsConstructor
public abstract class BaseProcessor {
    protected final ConversionService conversionService;
    protected final Validator validator;

    protected API.Match.Case<Exception, ErrorOutput> customCase(Throwable throwable, HttpStatus status,
                                                                Class<? extends Exception> e) {
        return Case($(instanceOf(e)), () -> ErrorOutput.builder()
                .errors(List.of(Error.builder()
                        .message(throwable.getMessage())
                        .build()))
                .statusCode(status).build());
    }

    protected API.Match.Case<Exception, ErrorOutput> defaultCase(Throwable throwable) {
        return Case($(),() -> ErrorOutput.builder()
                .errors(List.of(Error.builder()
                        .message(throwable.getMessage())
                        .build()))
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .build());
    }

}
