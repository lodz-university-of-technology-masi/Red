package com.masi.red.controller.api;

import com.masi.red.IUserService;
import com.masi.red.dto.UserDTO;
import com.masi.red.exception.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity addCandidate(@Valid @RequestBody UserDTO candidate) {
        try {
            UserDTO savedUser = userService.createCandidate(candidate);
            return ResponseEntity.ok(savedUser);
        } catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('MODERATOR')")
    @PostMapping(value = "/administrative")
    public ResponseEntity addAdministrativeUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            UserDTO savedUser = userService.createAdministrativeUser(userDTO);
            return ResponseEntity.ok(savedUser);
        } catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
