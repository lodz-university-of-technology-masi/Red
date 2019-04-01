package com.masi.red.dto;

import com.masi.red.entity.User;

import java.util.Set;

public class EditedTestDTO {

    private Integer id;
    private Integer jobTitleId;
    private Set<Integer> questionsIds;
    private User editor; //TODO replace with UserDTO
}
