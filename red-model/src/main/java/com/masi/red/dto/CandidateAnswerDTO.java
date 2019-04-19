package com.masi.red.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateAnswerDTO {
    private Integer id;
    private Integer questionId;
    private Integer testId;
    private String username;
    private String answer;
    private OffsetDateTime creationTime;
}
