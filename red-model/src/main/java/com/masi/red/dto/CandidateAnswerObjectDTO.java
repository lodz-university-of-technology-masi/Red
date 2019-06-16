package com.masi.red.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateAnswerObjectDTO {

    private Integer questionId;
    private String answer;
}
