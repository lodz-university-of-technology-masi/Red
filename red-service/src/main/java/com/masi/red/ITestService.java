package com.masi.red;

import com.masi.red.entity.Test;

import java.util.List;

public interface ITestService {

    Test addTest(Test test);
    List<Test> getAllTests();
    Test getTestById(Integer id);
    Test updateTest(Integer id, Test test);
    void deleteTest(Integer id);
}
