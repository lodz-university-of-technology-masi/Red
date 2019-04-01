package com.masi.red.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTestDTO {
    private Integer jobTitleId;
    private Set<Integer> questionsIds;
}
