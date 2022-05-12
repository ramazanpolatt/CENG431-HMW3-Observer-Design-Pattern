package com.mycompany.ceng431_hmw3.interfaces;

public enum Genre {

    ACOUSTIC(1),INSTRUMENTAL(2),ROCK(3),HIP_HOP(4),JAZZ(5),POP(6);

    private int value;
    Genre(int value) {
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
