package com.masi.red;

import com.masi.red.common.Language;
import com.masi.red.entity.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {

    @Query(value = "SELECT DISTINCT j FROM JobTitle j JOIN j.testList  AS tl WHERE tl.language=:language")
    List<JobTitle> findByTestLanguage( @Param("language") Language language);
}
