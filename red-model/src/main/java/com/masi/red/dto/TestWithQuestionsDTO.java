package com.masi.red.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestWithQuestionsDTO {

    private Integer id;
    private String editorName;
    private String jobTitleName;
    private OffsetDateTime creationTime;
    private List<QuestionDTO> questions;
}
