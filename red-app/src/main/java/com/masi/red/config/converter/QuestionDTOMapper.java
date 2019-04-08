package com.masi.red.config.converter;

import com.masi.red.dto.QuestionDTO;
import com.masi.red.entity.Question;
import com.masi.red.entity.Test;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionDTOMapper extends BidirectionalConverter<Question, QuestionDTO> {

    @Override
    public QuestionDTO convertTo(Question source, Type<QuestionDTO> destinationType, MappingContext mappingContext) {
        List<Integer> testIds = source.getTestList().stream()
                .map(Test::getId)
                .collect(Collectors.toList());
        return QuestionDTO.builder()
                .id(source.getId())
                .content(source.getContent())
                .creationTime(source.getCreationTime())
                .language(source.getLanguage())
                .testIds(testIds)
                .build();
    }

    @Override
    public Question convertFrom(QuestionDTO source, Type<Question> destinationType, MappingContext mappingContext) {
        return Question.builder()
                .id(source.getId())
                .content(source.getContent())
                .creationTime(source.getCreationTime())
                .language(source.getLanguage())
                .build();
    }
}
