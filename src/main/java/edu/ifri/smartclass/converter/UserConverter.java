package edu.ifri.smartclass.converter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import edu.ifri.smartclass.dto.UserDTO;
import edu.ifri.smartclass.model.User;

@Component
public class UserConverter {

    public UserDTO convert(User user){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> convert(List<User> users){
        return users.stream().map(this::convert).toList();
    }

}
