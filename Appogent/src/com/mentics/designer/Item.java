package com.mentics.designer;

public class Item {
    public final String name;
    public final int id;


    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
