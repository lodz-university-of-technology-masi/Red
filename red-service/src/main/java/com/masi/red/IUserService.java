package com.masi.red;

import com.masi.red.dto.UserDTO;
import com.masi.red.exception.DuplicateKeyException;

public interface IUserService {
    UserDTO createCandidate(UserDTO userDto) throws DuplicateKeyException;

    UserDTO createAdministrativeUser(UserDTO userDTO) throws DuplicateKeyException;
}
