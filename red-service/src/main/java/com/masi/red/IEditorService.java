package com.masi.red;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public interface IEditorService {

    Object createEditor(String name, String Surname);
    Object readEditor(Integer id);
    Object updateEditor(Integer id, String name, String surname);
    Object deleteEditor(Integer id);
    List<Object> getAllEditors();

}
