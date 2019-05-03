package com.masi.red;

import com.masi.red.common.RoleName;
import com.masi.red.dto.UserDto;
import com.masi.red.entity.Role;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperFacade mapper;
    private final PasswordEncoder encoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role userRole = Role.builder()
                .name(RoleName.CANDIDATE)
                .active(true)
                .build();

        user.setRoles(Collections.singleton(userRole));

        User savedUser = userRepository.save(user);

        UserDto response = mapper.map(savedUser, UserDto.class);
        response.setPassword("");
        return response;
    }
}
