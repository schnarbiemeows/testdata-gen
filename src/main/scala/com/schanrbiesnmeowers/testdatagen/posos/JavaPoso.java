package com.schanrbiesnmeowers.testdatagen.posos;

public class JavaPoso {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public JavaPoso(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
