package com.masi.red.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    private Set<RoleDTO> roles;
}
