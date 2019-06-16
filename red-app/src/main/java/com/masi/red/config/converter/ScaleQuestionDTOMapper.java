package com.masi.red.config.converter;

import com.masi.red.dto.ScaleQuestionDTO;
import com.masi.red.entity.ScaleQuestion;
import com.masi.red.entity.Test;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScaleQuestionDTOMapper extends BidirectionalConverter<ScaleQuestion, ScaleQuestionDTO> {

    @Override
    public ScaleQuestionDTO convertTo(ScaleQuestion source, Type<ScaleQuestionDTO> destinationType, MappingContext mappingContext) {
        List<Integer> testIds = source.getTestList().stream()
                .map(Test::getId)
                .collect(Collectors.toList());
        return ScaleQuestionDTO.builder()
                .id(source.getId())
                .content(source.getContent())
                .creationTime(source.getCreationTime())
                .language(source.getLanguage())
                .testIds(testIds)
                .minValue(source.getMinValue())
                .maxValue(source.getMaxValue())
                .interval(source.getInterval())
                .build();
    }

    @Override
    public ScaleQuestion convertFrom(ScaleQuestionDTO source, Type<ScaleQuestion> destinationType, MappingContext mappingContext) {
        return ScaleQuestion.builder()
                .id(source.getId())
                .content(source.getContent())
                .creationTime(source.getCreationTime())
                .language(source.getLanguage())
                .minValue(source.getMinValue())
                .maxValue(source.getMaxValue())
                .interval(source.getInterval())
                .testList(new ArrayList<>())
                .build();
    }
}
