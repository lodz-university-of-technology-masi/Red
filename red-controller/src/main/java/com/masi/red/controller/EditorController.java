package com.masi.red.controller;



import com.masi.red.IEditorService;
import com.masi.red.common.RoleName;
import com.masi.red.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
public class EditorController {

    @Autowired
    IEditorService editorService;

    @PostMapping(value="/redaktor")
    public ResponseEntity<Object> createEditor(@Valid @RequestBody User editor){

        editorService.createEditor(editor);

        return new ResponseEntity<>(editor, HttpStatus.CREATED);
    }

    @GetMapping(value="/redaktor/{editorId}")
    public ResponseEntity<User> readEditor(@PathVariable Integer editorId){

        User editor = editorService.readEditor(editorId);

        return new ResponseEntity<>(editor, HttpStatus.OK);
    }

    @PutMapping(value="/redaktor/{editorId}")
    public ResponseEntity<User> updateEditor(@PathVariable Integer editorId, @Valid @RequestBody User editor){


        editorService.updateEditor(editor, editorId);

        return new ResponseEntity<>(editor, HttpStatus.OK);
    }

    @DeleteMapping(value="/redaktor/{editorId}")
    public ResponseEntity<String> deleteEditor(@PathVariable Integer editorId){

        editorService.deleteEditor(editorId);

        return new ResponseEntity<>("Usunięto Redaktora o ID : " + editorId.toString() , HttpStatus.OK);
    }

    // Metody dodatkowe

    @GetMapping( value="/redaktor/all")
    public ResponseEntity<List<User>> getAllEditors(){

        List<User> editors = editorService.getAllEditors();

        return new ResponseEntity<>(editors, HttpStatus.OK);
    }

}