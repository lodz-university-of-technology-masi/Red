package com.masi.red.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class JobTitle {
    private int id;
    private String jobTitleName;
    private List<Test> testList = new ArrayList<>();
}
