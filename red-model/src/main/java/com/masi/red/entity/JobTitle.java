package com.masi.red.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_title")
public @Data class JobTitle {

    @Id
    @GeneratedValue(generator = "optimized-sequence")
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "jobTitle")
    private List<Test> testList = new ArrayList<>();

    @Column(name = "active")
    private boolean active;

    public void attachTest(Test test) {
        testList.add(test);
    }

    public void detachTest(Test test) {
        testList.remove(test);
    }
}
