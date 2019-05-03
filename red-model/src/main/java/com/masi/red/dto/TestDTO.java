package com.masi.red.dto;

import com.masi.red.common.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {

    private Integer id;
    private String editorName;
    private String jobTitleName;
    private int questionsNumber;
    private OffsetDateTime creationTime;
    private Language language;
}
