package com.masi.red.entity;

import lombok.Data;

@Data
public class Editor {

    private int id;
    private String name;
    private String surname;

    public Editor(int id, String name, String surname)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
