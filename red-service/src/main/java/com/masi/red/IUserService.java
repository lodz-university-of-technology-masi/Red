package com.masi.red;

import com.masi.red.dto.UserDTO;
import com.masi.red.exception.DuplicateKeyException;
import com.masi.red.exception.EntityNotFoundException;

import java.util.List;

public interface IUserService {
    UserDTO createCandidate(UserDTO userDto) throws DuplicateKeyException;

    UserDTO createAdministrativeUser(UserDTO userDTO) throws DuplicateKeyException;

    void deleteUserById(Integer id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserDTO userDTO, Integer userId);

    UserDTO getUserById(Integer userId) throws EntityNotFoundException;
}
