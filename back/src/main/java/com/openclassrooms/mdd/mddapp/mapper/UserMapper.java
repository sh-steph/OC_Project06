package com.openclassrooms.mdd.mddapp.mapper;

import com.openclassrooms.mdd.mddapp.dto.UserDto;
import com.openclassrooms.mdd.mddapp.models.User;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}
