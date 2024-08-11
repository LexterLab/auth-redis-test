package com.tinqinacademy.redis.api.operations.login;

import com.tinqinacademy.redis.api.base.OperationInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginInput implements OperationInput {

    @Schema(example = "domino")
    private String username;
    @Schema(example = "password")
    private String password;
}
