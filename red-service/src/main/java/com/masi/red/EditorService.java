package com.masi.red;

import com.masi.red.common.RoleName;
import com.masi.red.entity.Role;
import com.masi.red.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class EditorService implements IEditorService {

    @Autowired
    EditorRepository editorRepository;

    @Override
    public User createEditor(User editor) {

        Role role = new Role();
        role.setName(RoleName.EDITOR);

        editor.setRoles(Collections.singleton(role));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        String tmp = encoder.encode(editor.getPassword());

        editor.setPassword(tmp);

        System.out.println(editor.toString());

        return editorRepository.save(editor);
    }

    @Override
    public User readEditor(Integer id) {

        return editorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono redaktora o id " + id));
    }

    @Override
    public User updateEditor(User oldEditor, Integer editorId) {
        Role rolee = new Role();
        //rolee.setName(RoleName.EDITOR);

        if(oldEditor.getRoles().contains("MODERATOR")){
            rolee.setName(RoleName.MODERATOR);
        }
        if(oldEditor.getRoles().contains("EDITOR")){
            rolee.setName(RoleName.EDITOR);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        String tmp = encoder.encode(oldEditor.getPassword());

        User editor = User.builder()
                .id(editorId)
                .username(oldEditor.getUsername())
                .email(oldEditor.getEmail())
                .password(tmp)
                .roles(Collections.singleton(rolee))
                .firstName(oldEditor.getFirstName())
                .lastName(oldEditor.getLastName())
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
