package com.masi.red;

import com.masi.red.common.RoleName;
import com.masi.red.entity.Role;
import com.masi.red.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class EditorService implements IEditorService {

    @Autowired
    EditorRepository editorRepository;

    @Override
    public User createEditor(String username, String email, String password, RoleName role, String firstName, String lastName) {

        Role rolee = new Role();
        rolee.setName(RoleName.EDITOR);

        User editor = User.builder()
                .username(username)
                .email(email)
                .password(password)
                .roles(Collections.singleton(rolee))
                .firstName(firstName)
                .lastName(lastName)
                .build();


        return editorRepository.save(editor);
    }

    @Override
    public User readEditor(Integer id) {

        return editorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono redaktora o id " + id));
    }

    @Override
    public User updateEditor(Integer id, String username, String email, String password, RoleName role, String firstName, String lastName) {
        Role rolee = new Role();
        rolee.setName(RoleName.EDITOR);

        User editor = User.builder()
                .id(id)
                .username(username)
                .email(email)
                .password(password)
                .roles(Collections.singleton(rolee))
                .firstName(firstName)
                .lastName(lastName)
                .build();

        return editorRepository.save(editor);
    }

    @Override
    public void deleteEditor(Integer id) {

        editorRepository.deleteById(id);
    }

    @Override
    public List<User> getAllEditors() {

        return editorRepository.findAll();
    }
}
