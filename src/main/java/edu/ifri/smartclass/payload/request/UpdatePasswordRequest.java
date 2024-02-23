package edu.ifri.smartclass.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePasswordRequest(@NotNull
                                @NotBlank
                                 String currentPassword,
                                    @NotNull
                                @NotBlank
                                String newPassword,
                                    @NotNull
                                @NotBlank
                                String confirmPassword) {
}
