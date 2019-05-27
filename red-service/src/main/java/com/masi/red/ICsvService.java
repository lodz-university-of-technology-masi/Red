package com.masi.red;

import com.masi.red.dto.TestDTO;
import com.masi.red.entity.User;
import com.masi.red.exception.EmptyCsvFileException;
import com.masi.red.exception.InvalidCsvHeaderException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ICsvService {
    void exportTestCsv(Integer testId, HttpServletResponse response) throws IOException;

    TestDTO importTestCsv(MultipartFile file, User user) throws EmptyCsvFileException, IOException, InvalidCsvHeaderException;
}
