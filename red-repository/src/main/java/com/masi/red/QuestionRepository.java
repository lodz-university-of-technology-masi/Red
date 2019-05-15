package com.masi.red;

import com.masi.red.common.Language;
import com.masi.red.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query(value = "select question from Question question where (select count(test) from question.testList test where test.id = ?1) = 0 and question.language = ?2")
    List<Question> findNotAttachedQuestionsWithLanguage(Integer testId, Language language);
}
