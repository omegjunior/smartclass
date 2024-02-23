package edu.ifri.smartclass.payload.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class ClassroomRequest {
    @NotBlank(message = "Link should not be blank")
    @Size(max = 100, message = "Link should not exceed 100 caracters")
    private String link;

    @NotBlank(message = "name should not be blank")
    @Size(max = 100, message = "name should not exceed 100 caracters")
    private String name;

    @NotNull(message = "The userid, owner of the meeting link should not be null")
    private UUID userId;
}
