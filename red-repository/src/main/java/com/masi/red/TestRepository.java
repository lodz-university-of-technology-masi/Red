package com.masi.red;

import com.masi.red.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    List<Test> findAllByJobTitle_Id(Integer jobTitleId);

    List<Test> findAllByUser_Id(Integer userId);
}

