package com.masi.red.controller;



import com.masi.red.IEditorService;
import com.masi.red.entity.Editor;
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

    @PostMapping( value="{editorName}/{editorSurname}")
    public ResponseEntity<String> createEditor(@PathVariable String editorName, @PathVariable String editorSurname){

        String response = editorName + " " + editorSurname;

        editorService.createEditor(editorName,editorSurname);

        return new ResponseEntity<>("Created Editor : " + response, HttpStatus.CREATED);
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

//        Editor editor1 = new Editor(1,"Jan","Kowalski");
//        Editor editor2 = new Editor(2,"Janina","Kowalska");
//
//        List<Editor> editors = new ArrayList<>();
//        editors.add(editor1);
//        editors.add(editor2);
        List<Object> editors = editorService.getAllEditors();

        return new ResponseEntity<>(editors, HttpStatus.OK);
    }

}
