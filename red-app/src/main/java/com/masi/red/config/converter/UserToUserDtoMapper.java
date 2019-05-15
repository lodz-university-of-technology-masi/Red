package com.masi.red.config.converter;

import com.masi.red.dto.RoleDto;
import com.masi.red.dto.UserDto;
import com.masi.red.entity.Role;
import com.masi.red.entity.User;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserToUserDtoMapper extends BidirectionalConverter<User, UserDto> {
    @Override
    public UserDto convertTo(User source, Type<UserDto> destinationType, MappingContext mappingContext) {
        Set<RoleDto> roles = source.getRoles().stream().map(RoleDto::new).collect(Collectors.toSet());
        return UserDto.builder()
                .username(source.getUsername())
                .password(Strings.EMPTY)
                .email(source.getEmail())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .roles(roles)
                .build();
    }

    @Override
    public User convertFrom(UserDto source, Type<User> destinationType, MappingContext mappingContext) {
        Set<Role> roles = source.getRoles().stream().map(Role::new).collect(Collectors.toSet());
        return User.builder()
                .username(source.getUsername())
                .password(source.getPassword())
                .email(source.getEmail())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .roles(roles)
                .build();
    }
}
