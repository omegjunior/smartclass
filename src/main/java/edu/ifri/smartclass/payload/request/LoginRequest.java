package edu.ifri.smartclass.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(@NotNull
                           @Schema(example = "johndoe@gmail.com")
                           String usernameOrEmail,
                           @NotBlank
                           @Schema(example = "j1eb2e5a2-0f43-45fd-be37-9f182190ffc1")
                           String password) {
}
