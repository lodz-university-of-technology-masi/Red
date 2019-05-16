package com.masi.red.dto;

import com.masi.red.common.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Integer id;
    private RoleName name;
    private boolean active;
}
