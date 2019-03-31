package com.masi.red;

import com.masi.red.common.RoleName;
import com.masi.red.entity.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public interface IEditorService {

    User createEditor(String username, String email, String password, RoleName role, String firstName, String lastName);
    User readEditor(Integer id);
    User updateEditor(Integer id, String username, String email, String password, RoleName role, String firstName, String lastName);
    void deleteEditor(Integer id);
    List<User> getAllEditors();

}
