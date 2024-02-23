package edu.ifri.smartclass.converter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import edu.ifri.smartclass.dto.ClassroomDTO;
import edu.ifri.smartclass.model.Classroom;

@Component
public class ClassroomConverter {
    public ClassroomDTO convert(Classroom classroom){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(classroom, ClassroomDTO.class);
    }

    public List<ClassroomDTO> convert(List<Classroom> classrooms){
        return classrooms.stream().map(this::convert).toList();
    }
}
