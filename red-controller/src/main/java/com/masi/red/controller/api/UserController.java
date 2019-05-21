package com.masi.red.controller.api;

import com.masi.red.ITestService;
import com.masi.red.IUserService;
import com.masi.red.common.RoleName;
import com.masi.red.dto.UserDTO;
import com.masi.red.entity.Role;
import com.masi.red.entity.User;
import com.masi.red.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final IUserService userService;
    private final ITestService testService;

    @GetMapping(value = "/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id) {
        try {
            UserDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody UserDTO editor) {
        UserDTO updatedUser = userService.updateUser(editor, id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> editors = userService.getAllUsers();
        return ResponseEntity.ok(editors);
    }

    @PreAuthorize("hasAnyRole('MODERATOR', 'EDITOR')")
    @GetMapping(value = "/{userId}/tests")
    public ResponseEntity getUserTests(@PathVariable Integer userId, @AuthenticationPrincipal User user) {
        Set<@NotNull RoleName> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        if (roles.contains(RoleName.MODERATOR) || user.getId().equals(userId)) {
            return ResponseEntity.ok(testService.getTestsByUserId(userId));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
