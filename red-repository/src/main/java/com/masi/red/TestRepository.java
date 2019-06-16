package com.masi.red;

import com.masi.red.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    List<Test> findAllByJobTitleId(Integer jobTitleId);

    List<Test> findAllByUserId(Integer userId);
}

