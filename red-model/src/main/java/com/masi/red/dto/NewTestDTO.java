package com.masi.red.dto;

import com.masi.red.common.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewTestDTO {
    private Integer jobTitleId;
    private Integer editorId;
    private Language language;
    private List<QuestionDTO> questions;
}
