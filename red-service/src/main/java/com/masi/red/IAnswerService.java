package com.masi.red;

import com.masi.red.dto.CandidateAnswerDTO;
import com.masi.red.entity.User;

public interface IAnswerService {
    boolean addAnswers(CandidateAnswerDTO answer, User candidate);
}
