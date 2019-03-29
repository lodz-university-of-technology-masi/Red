package com.masi.red.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.prefs.Preferences;

@RestController
@RequestMapping(value = "/redaktor")
public class EditorController {

    @RequestMapping( value="{editorName}/{editorSurname}", method = RequestMethod.PUT)
    public Object createEditor(@PathVariable String editorName, @PathVariable String editorSurname){

        String response = editorName + " " + editorSurname;
        return new String("utworzono redaktora: " + response);
    }

    @RequestMapping( value="{editorId}/", method = RequestMethod.GET)
    public Object readEditor(@PathVariable Integer editorId){

        String response = editorId.toString();
        return new String("pobrano redaktora o ID: " + response);
    }

    @RequestMapping( value="{editorId}/{editorName}/{editorSurname}", method = RequestMethod.POST)
    public void updateEditor(@PathVariable Integer editorId, @PathVariable String editorName, @PathVariable String editorSurname){

        System.out.println("Aktualizacja redaktora o ID: " + editorId);
    }

    @RequestMapping( value="{editorId}", method = RequestMethod.DELETE)
    public void deleteEditor(@PathVariable Integer editorId){

        System.out.println("Usunięto redaktora o ID: " + editorId);
    }
}
