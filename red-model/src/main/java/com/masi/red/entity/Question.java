package com.masi.red.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masi.red.common.Language;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "question")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(generator = "optimized-sequence")
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @JsonIgnore
    @ManyToMany(mappedBy = "questions", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "original_question_id", nullable = false)
    private Question originalQuestion;

    @Column(name = "suggested_answer")
    private String suggestedAnswer;

    @PrePersist
    private void initializeCreationTime() {
        creationTime = OffsetDateTime.now();
    }

    public void attachToTest(Test test) {
        if(!testList.contains(test)) {
            testList.add(test);
        }
    }

    public void detachTest(int testId) {
        testList.removeIf(test -> test.getId() == testId);
    }
}
