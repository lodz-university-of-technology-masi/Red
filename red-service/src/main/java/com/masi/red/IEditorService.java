package com.masi.red;

import com.masi.red.entity.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public interface IEditorService {

    User createEditor(String name, String Surname);
    User readEditor(Integer id);
    User updateEditor(Integer id, String name, String surname);
    User deleteEditor(Integer id);
    List<User> getAllEditors();

}
