package com.github.artyitis.javaredditwrapper;

public enum Listings {
    CONTROVERSIAL, HOT, NEW, RANDOM, RISING, TOP;

    /**
     * To string.
     *
     * @return lowercase name
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
