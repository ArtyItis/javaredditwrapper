package com.github.artyitis.javaredditwrapper.subreddit;

public enum SubReddit_Type {
    PUBLIC, RESTRICTED;

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