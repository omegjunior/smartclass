package edu.ifri.smartclass.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest{
    @NotNull
    @Schema(example = "Assiba")
    private String firstName;

    @NotNull
    @Schema(example = "Kokou")
    private String lastName;

    @NotNull
    @Schema(example = "Sipan")
    private String userName;

    @Email
    @Schema(example = "doe@gmail.com")
   private String email;

    @NotNull
    @Schema(example = "Qwertyuiop")
    private String password;
}
