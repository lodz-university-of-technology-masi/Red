package com.masi.red.config.converter;

import com.masi.red.dto.RoleDTO;
import com.masi.red.dto.UserDTO;
import com.masi.red.entity.Role;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserToUserDTOMapper extends BidirectionalConverter<User, UserDTO> {

    private final MapperFacade mapper;

    @Override
    public UserDTO convertTo(User source, Type<UserDTO> destinationType, MappingContext mappingContext) {
        Set<RoleDTO> roles = source.getRoles().stream()
                .map(role -> mapper.map(role, RoleDTO.class))
                .collect(Collectors.toSet());
        return UserDTO.builder()
                .id(source.getId())
                .username(source.getUsername())
                .password(Strings.EMPTY)
                .email(source.getEmail())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .fullName(source.getFullName())
                .roles(roles)
                .build();
    }

    @Override
    public User convertFrom(UserDTO source, Type<User> destinationType, MappingContext mappingContext) {
        User.UserBuilder userBuilder = User.builder()
                .id(source.getId())
                .username(source.getUsername())
                .password(source.getPassword())
                .email(source.getEmail())
                .firstName(source.getFirstName())
                .lastName(source.getLastName());
        Set<RoleDTO> userDtoRoles = source.getRoles();
        if (userDtoRoles != null) {
            Set<Role> roles = userDtoRoles.stream()
                    .map(roleDto -> mapper.map(roleDto, Role.class))
                    .collect(Collectors.toSet());
            userBuilder.roles(roles);
        }
        return userBuilder
                .build();
    }
}
