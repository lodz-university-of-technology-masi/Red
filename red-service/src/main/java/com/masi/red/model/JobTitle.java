package com.masi.red.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class JobTitle {
    private int id;
    private List<Test> testList = new ArrayList<>();
    private Date creationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }
}
