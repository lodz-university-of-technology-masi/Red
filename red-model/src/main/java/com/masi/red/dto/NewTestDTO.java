package com.masi.red.dto;

import com.masi.red.common.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTestDTO {
    private Integer jobTitleId;
    private Language language;
    private Set<QuestionDTO> questions;
}
