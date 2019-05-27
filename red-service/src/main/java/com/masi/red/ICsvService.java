package com.masi.red;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ICsvService {
    void exportTestCsv(Integer testId, HttpServletResponse response) throws IOException;
}
