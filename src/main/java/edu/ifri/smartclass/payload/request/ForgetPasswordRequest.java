package edu.ifri.smartclass.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ForgetPasswordRequest(@NotNull
                                    @NotBlank
                                    String token,

                                    @NotNull
                                    @NotBlank
                                    String newPassword,

                                    @NotNull
                                    @NotBlank
                                    String confirmNewPassword) {

}
