package com.masi.red.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/redaktor")
public class EditorController {

    @ResponseBody @RequestMapping( value="{editorName}/{editorSurname}", method = RequestMethod.PUT)
    public Object createEditor(@PathVariable String editorName, @PathVariable String editorSurname){

        String response = editorName + " " + editorSurname;
        return new String("utworzono redaktora: " + response);
    }

    @ResponseBody @RequestMapping( value="{editorId}/", method = RequestMethod.GET)
    public Object readEditor(@PathVariable Integer editorId){

        String response = editorId.toString();
        return new String("pobrano redaktora o ID: " + response);
    }

    @ResponseBody @RequestMapping( value="{editorId}/{editorName}/{editorSurname}", method = RequestMethod.POST)
    public Object updateEditor(@PathVariable Integer editorId, @PathVariable String editorName, @PathVariable String editorSurname){

        String response = editorId.toString();
        return new String("aktualizacja redaktora o ID: " + response);
    }

    @ResponseBody @RequestMapping( value="{editorId}/{editorName}/{editorSurname}", method = RequestMethod.POST)
    public Object deleteEditor(@PathVariable Integer editorId, @PathVariable String editorName, @PathVariable String editorSurname){

        String response = editorId.toString();
        return new String("aktualizacja redaktora o ID: " + response);
    }
}
