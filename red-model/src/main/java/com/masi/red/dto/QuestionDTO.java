package com.masi.red.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.masi.red.common.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = NumericQuestionDTO.class, name = "Numeric"),
        @JsonSubTypes.Type(value = ScaleQuestionDTO.class, name = "Scale"),
        @JsonSubTypes.Type(value = SingleChoiceQuestionDTO.class, name = "SingleChoice"),
        @JsonSubTypes.Type(value = OpenQuestionDTO.class, name = "Open")
})
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class QuestionDTO {

    private Integer id;
    private List<Integer> testIds;
    private String content;
    private OffsetDateTime creationTime;
    private Language language;
    private Integer originalQuestionId;
    private String suggestedAnswer;
}
