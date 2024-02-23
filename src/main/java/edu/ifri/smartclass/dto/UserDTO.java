package edu.ifri.smartclass.dto;

import java.util.List;
import java.util.UUID;

import edu.ifri.smartclass.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Role role;
    //private ClassroomDTO classroom;

    //private List<ClassroomDTO> managedClassrooms;
    private List<ClassroomDTO> Classrooms;

/*     public void setManagedClassrooms(List<ClassroomDTO> managedClassrooms) {
        this.managedClassrooms = managedClassrooms;
    } */
}
