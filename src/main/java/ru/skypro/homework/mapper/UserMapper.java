package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.models.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "regDate", source = "entity.regDate",
            dateFormat = "dd-MM-yyyy HH:mm")
    UserDTO userToUserDTO(User entity);

    @Mapping(target = "regDate", source = "dto.regDate", ignore = true)
    @Mapping(target = "role", defaultValue = "USER")
    User userDTOToUser(UserDTO dto);

    List<UserDTO> userToUserDTOList(List<User> entities);

    List<User> userDTOToUserList(List<UserDTO> dto);
}