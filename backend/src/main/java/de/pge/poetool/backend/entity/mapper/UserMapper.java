package de.pge.poetool.backend.entity.mapper;

import de.pge.poetool.backend.entity.dto.UserDto;
import de.pge.poetool.backend.entity.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDto toDTO(final User user);

    User fromDTO(final UserDto userDto);
}
