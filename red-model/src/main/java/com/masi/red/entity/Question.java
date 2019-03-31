package com.masi.red.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(generator = "optimized-sequence")
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @ManyToMany(mappedBy = "questionsList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Test> testList = new ArrayList<>();

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @Column(name = "creation_time", nullable = false)
    private OffsetDateTime creationTime;
}
