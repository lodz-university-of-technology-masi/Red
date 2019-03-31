package com.masi.red;

import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
public class EditorService implements IEditorService {

    @Override
    public Integer createEditor(String name, String Surname) throws NotImplementedException {

        return new Integer(1); //TODO: jakieś prepared statement? żeby zwrócić ostatnie wstawione ID z DB
    }

    @Override
    public Object readEditor(Integer id) throws NotImplementedException {

        return new Object();
    }

    @Override
    public void updateEditor(Integer id, String name, String surname) throws NotImplementedException {

    }

    @Override
    public void deleteEditor(Integer id) throws NotImplementedException {

    }

    @Override
    public List<Object> getAllEditors() throws NotImplementedException {
        return null;
    }
}
