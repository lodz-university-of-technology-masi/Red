package com.masi.red.EditorService;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public interface IEditorService {

    Integer createEditor(String name, String Surname) throws NotImplementedException;

    Object readEditor(Integer id) throws NotImplementedException;

    void updateEditor(Integer id, String name, String surname) throws NotImplementedException;

    void deleteEditor(Integer id) throws NotImplementedException;
}
