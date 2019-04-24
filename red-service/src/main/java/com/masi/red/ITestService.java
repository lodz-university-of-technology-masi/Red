package com.masi.red;

import com.masi.red.dto.EditedTestDTO;
import com.masi.red.dto.NewTestDTO;
import com.masi.red.dto.TestDTO;
import com.masi.red.dto.TestWithQuestionsDTO;
import com.masi.red.entity.User;

import java.util.List;

public interface ITestService {

    TestDTO addTest(NewTestDTO test, User user);
    List<TestDTO> getAllTests();
    TestWithQuestionsDTO getTestById(Integer id);
    TestDTO updateTest(Integer id, EditedTestDTO test);
    void deleteTest(Integer id);
}
