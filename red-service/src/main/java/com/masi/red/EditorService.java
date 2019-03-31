package com.masi.red;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EditorService implements IEditorService {

    @Autowired
    EditorRepository editorRepository;

    @Override
    public Object createEditor(String name, String Surname) {

        //User editor = new User();
        //editor.setName(name);
        //editor.setSurname(surname);

        //return editorRepository.save(editor);

        return null;
    }

    @Override
    public Object readEditor(Integer id) {

//        return editorRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono redaktora o id " + id));

        return null;
    }

    @Override
    public Object updateEditor(Integer id, String name, String surname) {

        //User editor = new User();
        //editor.setId(id);
        //editor.setName(name);
        //editor.setSurname(surname);

        //editorRepository.save(editor);

        return null;
    }

    @Override
    public Object deleteEditor(Integer id) {

        //return editorRepository.deleteById(id);

        return null;
    }

    @Override
    public List<Object> getAllEditors() {

        //return editorRepository.findAll();
        return null;
    }
}
