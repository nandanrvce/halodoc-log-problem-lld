package com.halodoc.entities;


public class User implements IObserver {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void update(Object obj) {
        System.out.println(this.name+" received the update: "+ obj);
    }
}
