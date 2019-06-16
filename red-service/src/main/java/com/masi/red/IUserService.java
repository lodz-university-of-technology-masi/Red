package com.masi.red;

import com.masi.red.common.RoleName;
import com.masi.red.dto.UserDTO;
import com.masi.red.exception.DuplicateKeyException;

import java.util.List;

public interface IUserService {
    UserDTO createCandidate(UserDTO userDto) throws DuplicateKeyException;

    UserDTO createAdministrativeUser(UserDTO userDTO) throws DuplicateKeyException;

    void deleteUserById(Integer id);

    List<UserDTO> getAllUsers();

    List<UserDTO> getAllUsersByRole(RoleName roleName);

    UserDTO updateUser(UserDTO userDTO, Integer userId);

    UserDTO getUserById(Integer userId);
}
