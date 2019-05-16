package com.masi.red.config.converter;

import com.masi.red.dto.TestDTO;
import com.masi.red.entity.Test;
import com.masi.red.entity.User;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class TestDTOMapper extends BidirectionalConverter<Test, TestDTO> {

    @Override
    public TestDTO convertTo(Test source, Type<TestDTO> destinationType, MappingContext mappingContext) {
        User editor = source.getUser();
        return TestDTO.builder()
                .id(source.getId())
                .creationTime(source.getCreationTime())
                .editorName(editor != null ? editor.getFullName() : null)
                .jobTitleName(source.getJobTitle().getName())
                .questionsNumber(source.getQuestions() == null ? 0 : source.getQuestions().size())
                .build();
    }

    @Override
    public Test convertFrom(TestDTO source, Type<Test> destinationType, MappingContext mappingContext) {
        return Test.builder()
                .id(source.getId())
                .creationTime(source.getCreationTime())
                .build();
    }
}
