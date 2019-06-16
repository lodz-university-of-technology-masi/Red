package com.masi.red.config.converter;

import com.masi.red.dto.SingleChoiceQuestionDTO;
import com.masi.red.entity.SingleChoiceQuestion;
import com.masi.red.entity.Test;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SingleChoiceQuestionDTOMapper extends BidirectionalConverter<SingleChoiceQuestion, SingleChoiceQuestionDTO> {

    @Override
    public SingleChoiceQuestionDTO convertTo(SingleChoiceQuestion source, Type<SingleChoiceQuestionDTO> destinationType, MappingContext mappingContext) {
        List<Integer> testIds = source.getTestList().stream()
                .map(Test::getId)
                .collect(Collectors.toList());
        return SingleChoiceQuestionDTO.builder()
                .id(source.getId())
                .content(source.getContent())
                .creationTime(source.getCreationTime())
                .language(source.getLanguage())
                .testIds(testIds)
                .possibleAnswers(source.getPossibleAnswers())
                .build();
    }

    @Override
    public SingleChoiceQuestion convertFrom(SingleChoiceQuestionDTO source, Type<SingleChoiceQuestion> destinationType, MappingContext mappingContext) {
        return SingleChoiceQuestion.builder()
                .id(source.getId())
                .content(source.getContent())
                .creationTime(source.getCreationTime())
                .language(source.getLanguage())
                .possibleAnswers(source.getPossibleAnswers())
                .testList(new ArrayList<>())
                .build();
    }
}
