package com.masi.red.controller;



import com.masi.red.IEditorService;
import com.masi.red.common.RoleName;
import com.masi.red.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/redaktor")
public class EditorController {

    @Autowired
    IEditorService editorService;

    @PostMapping( value="{username}/{email}/{password}/{role}/{firstName}/{lastName}")
    public ResponseEntity<String> createEditor(@PathVariable String username,@PathVariable String email,@PathVariable String password,
                                               @PathVariable RoleName role,@PathVariable String firstName,@PathVariable String lastName){

        String response = firstName + " " + lastName;

        editorService.createEditor(username,email, password, role, firstName, lastName);

        return new ResponseEntity<>("Created Editor : " + response, HttpStatus.CREATED);
    }

    @GetMapping( value="{editorId}")
    public ResponseEntity<User> readEditor(@PathVariable Integer editorId){

        User editor = editorService.readEditor(editorId);

        return new ResponseEntity<>(editor, HttpStatus.OK);
    }

    @PutMapping( value="{editorId}/{username}/{email}/{password}/{role}/{firstName}/{lastName}")
    public ResponseEntity<String> updateEditor(@PathVariable Integer editorId, @PathVariable String username,@PathVariable String email,@PathVariable String password,
                                               @PathVariable RoleName role,@PathVariable String firstName,@PathVariable String lastName){

        editorService.updateEditor(editorId, username, email, password, role, firstName, lastName);

        return new ResponseEntity<>("Aktualizacja Redaktora o ID:" + editorId.toString() , HttpStatus.OK);
    }

    @DeleteMapping( value="{editorId}")
    public ResponseEntity<String> deleteEditor(@PathVariable Integer editorId){

        editorService.deleteEditor(editorId);

        return new ResponseEntity<>("Usunięto Redaktora o ID : " + editorId.toString() , HttpStatus.OK);
    }

    // Metody dodatkowe

    @GetMapping( value="all")
    public ResponseEntity<List<User>> getAllEditors(){

        List<User> editors = editorService.getAllEditors();

        return new ResponseEntity<>(editors, HttpStatus.OK);
    }

}
