package com.masi.red;

import com.masi.red.dto.QuestionDTO;

import java.util.List;

public interface IQuestionService {

    List<QuestionDTO> findAll();
    List<QuestionDTO> findNotAttachedQuestions(Integer testId);
}
