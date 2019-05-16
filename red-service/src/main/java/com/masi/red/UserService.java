package com.masi.red;

import com.masi.red.common.RoleName;
import com.masi.red.dto.UserDTO;
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
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final MapperFacade mapper;
    private final PasswordEncoder encoder;

    @Override
    public UserDTO createCandidate(UserDTO userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(encoder.encode(user.getPassword()));

        Role candidateRole = Role.builder().name(RoleName.CANDIDATE).active(true).build();
        user.setRoles(Collections.singleton(candidateRole));

        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO createAdministrativeUser(UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        user.setPassword(encoder.encode(userDTO.getPassword()));

        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDTO.class);
    }
}
