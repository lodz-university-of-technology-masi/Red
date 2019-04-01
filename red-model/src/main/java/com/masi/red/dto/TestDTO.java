package com.masi.red.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class TestDTO {

    private Integer id;
    private String editorName;
    private String jobTitleName;
    private int questionsNumber;
    private OffsetDateTime creationDate;
}
