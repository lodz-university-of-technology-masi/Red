package com.masi.red;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;


public interface IEditorService {

    Integer createEditor(String name, String Surname) throws NotImplementedException;

    Object readEditor(Integer id) throws NotImplementedException;

    void updateEditor(Integer id, String name, String surname) throws NotImplementedException;

    void deleteEditor(Integer id) throws NotImplementedException;

    List<Object> getAllEditors() throws NotImplementedException;

}
