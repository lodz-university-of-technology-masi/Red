package com.masi.red;

import com.masi.red.common.QuestionTypeMapper;
import com.masi.red.dto.QuestionDTO;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final MapperFacade mapper;

    @Override
    public List<QuestionDTO> findAll() {
        return questionRepository.findAll().stream()
                .map(question -> mapper.map(question, QuestionTypeMapper.getDTOClass(question)))
                .collect(Collectors.toList());

    }
}
