package com.tinqinacademy.redis.core.processors;


import com.tinqinacademy.redis.api.errors.ErrorOutput;
import com.tinqinacademy.redis.api.operations.login.Login;
import com.tinqinacademy.redis.api.operations.login.LoginInput;
import com.tinqinacademy.redis.api.operations.login.LoginOutput;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static io.vavr.API.Match;

@Service
@Slf4j
public class LoginProcessor extends BaseProcessor implements Login {
    private final AuthenticationManager authenticationManager;

    public LoginProcessor(ConversionService conversionService, Validator validator,
                          AuthenticationManager authenticationManager) {
        super(conversionService, validator);
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Either<ErrorOutput, LoginOutput> process(LoginInput input) {
        log.info("Start login {}", input.getUsername());
        return Try.of(() -> {
            authenticate(input);
            log.info("End login {}", input.getUsername());
            return LoginOutput.builder().success(true).build();

        }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        customCase(throwable, HttpStatus.BAD_REQUEST, AuthenticationException.class),
                        defaultCase(throwable)
                ));
    }

    private void authenticate(LoginInput input) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
