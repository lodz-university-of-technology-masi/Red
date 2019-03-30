package com.masi.red.controller;



import com.masi.red.EditorService.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping(value = "/redaktor")
public class EditorController {

    @Autowired
    EditorService editorService;

    @PostMapping( value="{editorName}/{editorSurname}")
    public ResponseEntity<String> createEditor(@PathVariable String editorName, @PathVariable String editorSurname){

        String response = editorName + " " + editorSurname;

        Integer editorID = editorService.createEditor(editorName,editorSurname);

        return new ResponseEntity<>("Created Editor ID : " + editorID, HttpStatus.OK);
    }

    @GetMapping( value="{editorId}")
    public ResponseEntity<Object> readEditor(@PathVariable Integer editorId){

        Object editor = editorService.readEditor(editorId);

        return new ResponseEntity<>(editor, HttpStatus.OK);
    }

    @PutMapping( value="{editorId}/{editorName}/{editorSurname}")
    public ResponseEntity<String> updateEditor(@PathVariable Integer editorId, @PathVariable String editorName, @PathVariable String editorSurname){

        editorService.updateEditor(editorId, editorName, editorSurname);

        return new ResponseEntity<>("Aktualizacja Redaktora", HttpStatus.OK);
    }

    @DeleteMapping( value="{editorId}")
    public ResponseEntity<String> deleteEditor(@PathVariable Integer editorId){

        editorService.deleteEditor(editorId);

        return new ResponseEntity<>("Usunięto Redaktora o ID : " + editorId, HttpStatus.OK);
    }

    // Metody dodatkowe

    @GetMapping( value="all")
    public ResponseEntity<Object> getAllEditors(){

        List<Object> editor = editorService.getAllEditors();

        return new ResponseEntity<>(editor, HttpStatus.OK);
    }

}
