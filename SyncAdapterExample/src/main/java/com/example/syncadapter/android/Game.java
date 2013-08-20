package com.example.syncadapter.android;

public class Game {
    private final String title;
    private final int year;

    public Game(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }
}
