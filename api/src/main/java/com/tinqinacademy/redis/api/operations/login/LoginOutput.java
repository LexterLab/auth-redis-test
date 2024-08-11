package com.tinqinacademy.redis.api.operations.login;

import com.tinqinacademy.redis.api.base.OperationOutput;
import lombok.Builder;


@Builder
public class LoginOutput implements OperationOutput {
    private Boolean success;
}
