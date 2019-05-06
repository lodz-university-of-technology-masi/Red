package com.masi.red;

import com.masi.red.entity.CandidateAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateAnswerRepository extends JpaRepository<CandidateAnswer, Integer> {
}
