package com.TABOO.Backend;

public class Likes {
    // Atributos
    private int likes;

    // Constructor
    public Likes() {
        this.likes = 0;
    }

    // Metodos
    public void incrementLikes() {
        this.likes++;
    }

    public void decrementLikes() {
        this.likes--;
    }

    public int getLikes() {
        return this.likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
