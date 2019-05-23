package com.masi.red;

import com.masi.red.common.RoleName;
import com.masi.red.dto.UserDTO;
import com.masi.red.entity.Role;
import com.masi.red.entity.User;
import com.masi.red.exception.DuplicateKeyException;
import com.masi.red.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final MapperFacade mapper;
    private final PasswordEncoder encoder;

    @Override
    public UserDTO createCandidate(UserDTO userDto) throws DuplicateKeyException {
        if (userRepository.existsByUsernameOrEmail(userDto.getUsername(), userDto.getEmail())) {
            throw new DuplicateKeyException("Użytkownik z podaną nazwą lub emailem juz istnieje!");
        }
        User user = mapper.map(userDto, User.class);
        user.setPassword(encoder.encode(user.getPassword()));

        Role candidateRole = Role.builder().name(RoleName.CANDIDATE).active(true).build();
        user.setRoles(Collections.singleton(candidateRole));

        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO createAdministrativeUser(UserDTO userDTO) throws DuplicateKeyException {
        if (userRepository.existsByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail())) {
            throw new DuplicateKeyException("Użytkownik z podaną nazwą lub emailem juz istnieje!");
        }
        User user = mapper.map(userDTO, User.class);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllUsersByRole(RoleName roleName) {
        return userRepository.findAllByRolesNameIn(roleName).stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        userDTO.setId(userId);
        User user = mapper.map(userDTO, User.class);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        User updatedUser = userRepository.save(user);
        return mapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Integer userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono użytkownika o id: " + userId));
        return mapper.map(user, UserDTO.class);
    }
}
