package com.masi.red.helper;

import com.masi.red.JobTitleRepository;
import com.masi.red.QuestionRepository;
import com.masi.red.TestRepository;
import com.masi.red.UserRepository;
import com.masi.red.entity.JobTitle;
import com.masi.red.entity.Question;
import com.masi.red.entity.Test;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityFinder {

    private final UserRepository userRepository;
    private final JobTitleRepository jobTitleRepository;
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono użytkownika o loginie " + username));
    }

    public JobTitle findJobTitleById(Integer id) {
        return jobTitleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono stanowiska o id " + id));
    }

    public Test findTestById(Integer id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie ma testu o id " + id));
    }

    public Question findQuestionById(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie ma pytania o id " + id));
    }
}
