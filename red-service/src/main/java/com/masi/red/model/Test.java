package com.masi.red.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public @Data class Test {
    private int id;
    private List<Question> questionsList = new ArrayList<>();
    private JobTitle jobTitle;
    private OffsetDateTime creationDate;
    //TO DO - dodac id usera
}
