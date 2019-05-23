package com.masi.red.controller.api;

import com.masi.red.IUserService;
import com.masi.red.common.RoleName;
import com.masi.red.dto.UserDTO;
import com.masi.red.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final IUserService userService;

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
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(required = false) RoleName role) {
        List<UserDTO> users;
        if (role != null) {
            users = userService.getAllUsersByRole(role);
        } else {
            users = userService.getAllUsers();
        }
        return ResponseEntity.ok(users);
    }
}
