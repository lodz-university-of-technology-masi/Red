package com.masi.red.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateAnswerDTO {
    private Integer id;
    private Integer testId;
    private String username;
    private List<CandidateAnswerObjectDTO> answers;
    private OffsetDateTime creationTime;

}
