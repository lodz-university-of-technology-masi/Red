package com.masi.red.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public @Data class Question {
    private int id ;
    private List<Test> testList = new ArrayList<>();
    private String content;
    private OffsetDateTime creationDate;
}
