package com.masi.red;

import com.masi.red.dto.UserDTO;

public interface IUserService {
    UserDTO createCandidate(UserDTO userDto);
    UserDTO createAdministrativeUser(UserDTO userDTO);
}
