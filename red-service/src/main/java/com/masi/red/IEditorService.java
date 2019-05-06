package com.masi.red;

import com.masi.red.common.RoleName;
import com.masi.red.entity.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public interface IEditorService {

    User createEditor(User editor);
    User readEditor(Integer id);
    User updateEditor(User oldEditor, Integer editorId);
    void deleteEditor(Integer id);
    List<User> getAllEditors();

}
