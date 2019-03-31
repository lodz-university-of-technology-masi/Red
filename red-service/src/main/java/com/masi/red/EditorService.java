package com.masi.red;

import com.masi.red.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EditorService implements IEditorService {

    @Autowired
    EditorRepository editorRepository;

    @Override
    public User createEditor(String firstName, String lastName) {

        User editor = new User();
        editor.setFirstName(firstName);
        editor.setLastName(lastName);

        return editorRepository.save(editor);

        return null;
    }

    @Override
    public User readEditor(Integer id) {

        return editorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono redaktora o id " + id));

    }

    @Override
    public User updateEditor(Integer id, String name, String surname) {

        User editor = new User();
        editor.setId(id);
        editor.setFirstName(name);
        editor.setLastName(surname);

        return editorRepository.save(editor);
    }

    @Override
    public User deleteEditor(Integer id) {

        return editorRepository.deleteById(id);
    }

    @Override
    public List<User> getAllEditors() {

        return editorRepository.findAll();
    }
}
