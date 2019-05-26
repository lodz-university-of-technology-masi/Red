package com.masi.red;

import com.masi.red.dto.*;
import com.masi.red.entity.User;
import com.masi.red.exception.NoTestsAvailableException;
import com.masi.red.exception.ResourceAccessForbiddenException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ITestService {

    TestDTO addTest(NewTestDTO testDTO, User user);

    List<TestDTO> getAllTests();

    TestWithQuestionsDTO getTestById(Integer id);

    TestWithQuestionsDTO getRandomTest(Integer jobTitleId, Integer userId) throws NoTestsAvailableException;

    TestDTO updateTest(Integer id, EditedTestDTO test, User user) throws ResourceAccessForbiddenException;

    void deleteTest(Integer testId, User user) throws ResourceAccessForbiddenException;

    void detachQuestionFromTest(Integer testId, Integer questionId, User user) throws ResourceAccessForbiddenException;

    void attachQuestionToTest(QuestionDTO question, Integer testId, User user) throws ResourceAccessForbiddenException;

    List<TestDTO> getTestsByUserId(User user);

    TestDTO importTest(MultipartFile file);

    void exportTest(Integer testId, HttpServletResponse response) throws IOException;
}
