package com.masi.red.common;

public class CsvConstants {
    public static final char CSV_SEPARATOR = ';';
    public static final String MEDIA_TYPE = "text/csv";
    public static final String HEADER_KEY = "Content-Disposition";
    public static final String HEADER_TEST_VALUE = "attachment; filename=test.csv";
    public static final String DEFAULT_ARRAY_OPEN = "[";
    public static final String DEFAULT_ARRAY_CLOSE = "]";
    public static final String DEFAULT_SUGGESTED_ANSWER_MARKER = "|";
    public static final String DEFAULT_POSSIBLE_ANSWERS_DELIMITER = ",";
    public static final String DEFAULT_POSSIBLE_ANSWERS_NUMBER = "1";

    private CsvConstants() {
    }
}
