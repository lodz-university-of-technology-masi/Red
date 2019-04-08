package com.masi.red.controller;

import com.masi.red.IQuestionService;
import com.masi.red.dto.QuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionController {

    private final IQuestionService questionService;

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionDTO>> findAllQuestions() {
        return ResponseEntity.ok(questionService.findAll());
    }
}
