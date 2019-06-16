package com.masi.red;

import com.masi.red.common.Language;
import com.masi.red.dto.QuestionDTO;

import java.util.List;

public interface IQuestionService {

    List<QuestionDTO> findAll();
    List<QuestionDTO> findNotAttachedQuestionsWithLanguage(Integer testId, Language language);
}
