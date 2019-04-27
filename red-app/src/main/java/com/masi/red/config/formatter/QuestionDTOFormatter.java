package com.masi.red.config.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masi.red.QuestionRepository;
import com.masi.red.common.QuestionTypeMapper;
import com.masi.red.dto.NumericQuestionDTO;
import com.masi.red.dto.QuestionDTO;
import com.masi.red.entity.Question;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionDTOFormatter implements Formatter<QuestionDTO> {

    private final QuestionRepository questionRepository;
    private final MapperFacade mapper;
    private final ObjectMapper objectMapper;

    @Override
    public QuestionDTO parse(String text, Locale locale) throws ParseException {
        Integer id = Integer.valueOf(text);
        Question question = questionRepository.findById(id).get();
        return mapper.map(question, QuestionTypeMapper.getDTOClass(question));
    }

    @Override
    public String print(QuestionDTO object, Locale locale) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
