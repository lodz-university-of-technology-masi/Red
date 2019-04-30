package com.masi.red.dto;

import com.masi.red.common.Language;
import com.masi.red.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditedTestDTO {

    private Integer id;
    private Integer jobTitleId;
    private Set<Integer> questionsIds;
    private Language language;
    private User editor; //TODO replace with UserDTO
}
