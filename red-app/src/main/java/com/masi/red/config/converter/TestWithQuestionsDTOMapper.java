package com.masi.red.config.converter;

import com.masi.red.common.QuestionTypeMapper;
import com.masi.red.dto.QuestionDTO;
import com.masi.red.dto.TestWithQuestionsDTO;
import com.masi.red.entity.Question;
import com.masi.red.entity.Test;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EqualsAndHashCode(callSuper = true)
public class TestWithQuestionsDTOMapper extends BidirectionalConverter<Test, TestWithQuestionsDTO> {

    private final MapperFacade mapper;

    @Override
    public TestWithQuestionsDTO convertTo(Test source, Type<TestWithQuestionsDTO> destinationType, MappingContext mappingContext) {
        List<QuestionDTO> questions = source.getQuestions()
                .stream().map(question -> mapper.map(question, QuestionTypeMapper.getDTOClass(question))).collect(Collectors.toList());
        return TestWithQuestionsDTO.builder()
                .id(source.getId())
                .questions(questions)
                .build();
    }

    @Override
    public Test convertFrom(TestWithQuestionsDTO source, Type<Test> destinationType, MappingContext mappingContext) {
        List<Question> questions = source.getQuestions()
                .stream().map(question -> mapper.map(question, QuestionTypeMapper.getEntityClass(question))).collect(Collectors.toList());
        return Test.builder()
                .id(source.getId())
                .questions(questions)
                .build();
    }
}
