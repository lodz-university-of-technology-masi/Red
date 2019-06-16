package com.masi.red.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate_answer")
public class CandidateAnswer {

    @Id
    @GeneratedValue(generator = "optimized-sequence")
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @NotNull
    @Column(name = "answer",nullable = false)
    private String answer;

    @NotNull
    @Column(name = "creation_time", nullable = false)
    private OffsetDateTime creationTime;

    @PrePersist
    private void initializeCreationTime() {
        creationTime = OffsetDateTime.now();
    }
}
