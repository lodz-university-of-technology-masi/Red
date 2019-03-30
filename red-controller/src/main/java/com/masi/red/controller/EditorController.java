package com.masi.red.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/redaktor")
public class EditorController {

    @PutMapping( value="{editorName}/{editorSurname}")
    public ResponseEntity<String> createEditor(@PathVariable String editorName, @PathVariable String editorSurname){

        String response = editorName + " " + editorSurname;

        return new ResponseEntity<>("Created Editor: " + response, HttpStatus.OK);
    }

    @GetMapping( value="{editorId}/")
    public ResponseEntity<Object> readEditor(@PathVariable Integer editorId){

        //Object editor = editorService.getEditorByID(editorId);
        Object editor = editorId.toString();

        return new ResponseEntity<>(editor, HttpStatus.OK);
    }

    @PostMapping( value="{editorId}/{editorName}/{editorSurname}")
    public void updateEditor(@PathVariable Integer editorId, @PathVariable String editorName, @PathVariable String editorSurname){

    }

    @DeleteMapping( value="{editorId}")
    public ResponseEntity<String> deleteEditor(@PathVariable Integer editorId){

        //Object editor = editorService.getEditorByID(editorId);

        return new ResponseEntity<>("Usunięto Redaktora o ID : " + editorId, HttpStatus.OK);
    }
}
