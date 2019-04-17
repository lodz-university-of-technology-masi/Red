package com.masi.red.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "single_choice_question")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SingleChoiceQuestion extends Question {

    @ElementCollection
    @NotNull
    @Column(name = "possible_answers", nullable = false)
    private Set<String> possibleAnswers;
}
