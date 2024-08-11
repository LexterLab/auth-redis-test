package com.tinqinacademy.redis.rest.controllers;

import com.tinqinacademy.redis.api.errors.ErrorOutput;
import com.tinqinacademy.redis.api.operations.login.Login;
import com.tinqinacademy.redis.api.operations.login.LoginInput;
import com.tinqinacademy.redis.api.operations.login.LoginOutput;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/login")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final Login login;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginInput input) {
        Either<ErrorOutput, LoginOutput> output = login.process(input);
        return handleOutput(output, HttpStatus.CREATED);
    }

}
