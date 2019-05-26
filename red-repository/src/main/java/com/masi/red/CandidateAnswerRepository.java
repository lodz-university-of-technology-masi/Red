package com.masi.red;

import com.masi.red.entity.CandidateAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateAnswerRepository extends JpaRepository<CandidateAnswer, Integer> {
    List<CandidateAnswer> findAllByUserId(Integer userId);
}
