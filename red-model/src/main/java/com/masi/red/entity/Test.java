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
@Entity
@Table(name = "test")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Id
    @GeneratedValue(generator = "optimized-sequence")
    @Column(name = "id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private int id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "test_question",
            joinColumns = {@JoinColumn(name = "test_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")})
    private List<Question> questions = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "job_title_id")
    private JobTitle jobTitle;

    @NotNull
    @Column(name = "creation_time", nullable = false)
    private OffsetDateTime creationTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private Language language;

    @PrePersist
    private void initializeCreationTime() {
        creationTime = OffsetDateTime.now();
    }
}
