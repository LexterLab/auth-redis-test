package com.tinqinacademy.redis.api.base;


import com.tinqinacademy.redis.api.errors.ErrorOutput;
import io.vavr.control.Either;

public interface OperationProcessor <I extends OperationInput, O extends OperationOutput> {
    Either<ErrorOutput, O> process(I input);
}
