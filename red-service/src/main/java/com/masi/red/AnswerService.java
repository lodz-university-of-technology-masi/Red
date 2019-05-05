package com.masi.red;

import com.masi.red.dto.CandidateAnswerDTO;
import com.masi.red.entity.CandidateAnswer;
import com.masi.red.entity.Question;
import com.masi.red.entity.Test;
import com.masi.red.entity.User;
import com.masi.red.helper.EntityFinder;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnswerService implements IAnswerService {
    private final CandidateAnswerRepository candidateAnswerRepository;
    private final MapperFacade mapper;
    private final EntityFinder entityFinder;

    @Override
    public CandidateAnswerDTO addAnswers(CandidateAnswerDTO answer, int testQuestionNo) {
            User user = entityFinder.findUserByUsername(answer.getUsername());
            Test test = entityFinder.findTestById(answer.getTestId());
            Question question = entityFinder.findQuestionById(answer.getAnswers().get(testQuestionNo).getQuestionId());
            String answerStr = answer.getAnswers().get(testQuestionNo).getAnswer();
            CandidateAnswer candidateAnswer = CandidateAnswer.builder()
                    .user(user)
                    .test(test)
                    .question(question)
                    .answer(answerStr)
            .build();

        return mapper.map(candidateAnswerRepository.save(candidateAnswer),CandidateAnswerDTO.class);
    }
}
