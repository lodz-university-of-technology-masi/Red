package com.masi.red.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "job_title")
public class JobTitle {

    @Id
    @GeneratedValue(generator = "optimized-sequence")
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
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
