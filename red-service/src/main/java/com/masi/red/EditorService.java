package com.masi.red;

import com.masi.red.common.RoleName;
import com.masi.red.dto.UserDTO;
import com.masi.red.entity.Role;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EditorService implements IEditorService {

    private final UserRepository userRepository;
    private final MapperFacade mapper;

    @Override
    public User createEditor(User editor) {

        Role role = new Role();
        role.setName(RoleName.EDITOR);

        editor.setRoles(Collections.singleton(role));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        String tmp = encoder.encode(editor.getPassword());

        editor.setPassword(tmp);

        return userRepository.save(editor);
    }

    @Override
    public User readEditor(Integer id) {

        return userRepository.findById(id)
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

        return userRepository.save(editor);
    }

    @Override
    public void deleteEditor(Integer id) {

        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> getAllEditors() {
        return userRepository.findAllByRolesNameIn(RoleName.EDITOR).stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
