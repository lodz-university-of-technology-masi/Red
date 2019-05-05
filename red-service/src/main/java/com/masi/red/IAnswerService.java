package com.masi.red;

import com.masi.red.dto.CandidateAnswerDTO;

public interface IAnswerService {
    CandidateAnswerDTO addAnswers(CandidateAnswerDTO answer, int testQuestionNo);
}
