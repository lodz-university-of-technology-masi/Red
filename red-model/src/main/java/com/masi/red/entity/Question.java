package com.masi.red.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masi.red.common.Language;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "question")
public class Question {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(generator = "optimized-sequence")
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @JsonIgnore
    @ManyToMany(mappedBy = "questionsList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Test> testList = new ArrayList<>();

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @Column(name = "creation_time", nullable = false)
    private OffsetDateTime creationTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private Language language;

    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "original_question_id", nullable = false)
    private Question originalQuestion;

    @PrePersist
    private void initializeCreationTime() {
        creationTime = OffsetDateTime.now();
    }
}
