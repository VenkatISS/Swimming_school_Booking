package com.hemanth.entities;

public class Trainer {
    private String name;
    public Trainer(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Trainer) {
            return this.name.equals(((Trainer) obj).name);
        }
        return false;
    }
}
