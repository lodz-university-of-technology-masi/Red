package com.masi.red.controller.api;

import com.masi.red.IUsabilityDataService;
import com.masi.red.entity.UsabilityData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usabilityData")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsabilityDataController {

    private final IUsabilityDataService usabilityDataService;

    @PostMapping
    public ResponseEntity<UsabilityData> persist(@Valid @RequestBody UsabilityData usabilityData) {
        return ResponseEntity.ok(usabilityDataService.persist(usabilityData));
    }
}
