package com.masi.red.dto;

import com.masi.red.common.RoleName;
import com.masi.red.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Integer id;
    private RoleName name;
    private boolean active;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.active = role.isActive();
    }
}
