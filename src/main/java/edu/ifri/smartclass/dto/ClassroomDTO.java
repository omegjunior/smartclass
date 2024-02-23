package edu.ifri.smartclass.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDTO {
    private Long id;

    private String link;

    private String name;
    
    @JsonIgnore
    private UserDTO user;
}
