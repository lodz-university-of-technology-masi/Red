package com.masi.red;

import com.masi.red.dto.NewTestDTO;
import com.masi.red.dto.TestDTO;
import com.masi.red.entity.Test;

import java.util.List;

public interface ITestService {

    Test addTest(NewTestDTO test);
    List<TestDTO> getAllTests();
    Test getTestById(Integer id);
    Test updateTest(Integer id, Test test);
    void deleteTest(Integer id);
}
