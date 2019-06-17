package com.masi.red.config.converter;

import com.masi.red.common.QuestionTypeMapper;
import com.masi.red.dto.NewTestDTO;
import com.masi.red.dto.QuestionDTO;
import com.masi.red.dto.TestDTO;
import com.masi.red.entity.Question;
import com.masi.red.entity.Test;
import com.masi.red.entity.User;
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
public class NewTestDTOMapper extends BidirectionalConverter<Test, NewTestDTO> {
    private final MapperFacade mapper;

    @Override
    public NewTestDTO convertTo(Test source, Type<NewTestDTO> destinationType, MappingContext mappingContext) {
        User editor = source.getUser();
        List<QuestionDTO> questions = source.getQuestions().stream()
                .map(question -> mapper.map(question, QuestionTypeMapper.getDTOClass(question)))
                .collect(Collectors.toList());
        return NewTestDTO.builder()
                .editorId(editor != null ? editor.getId() : null)
                .jobTitleId(source.getJobTitle().getId())
                .questions(questions)
                .language(source.getLanguage())
                .build();
    }

    @Override
    public Test convertFrom(NewTestDTO source, Type<Test> destinationType, MappingContext mappingContext) {
        return Test.builder()
                .language(source.getLanguage())
                .build();
    }
}
