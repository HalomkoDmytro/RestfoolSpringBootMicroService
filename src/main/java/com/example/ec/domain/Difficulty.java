package com.example.ec.domain;

public enum Difficulty {
    Easy,
    Medium,
    Difficult,
    Varies;

    public static Difficulty findByLabel(String name) {
        for (Difficulty dif : Difficulty.values()) {
            if (dif.toString().equals(name)) {
                return dif;
            }
        }
        return null;
    }
}
