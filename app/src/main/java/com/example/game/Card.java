package com.example.game;

public class Card {
    private int imageResourceId;
    private String name; // Add this field to store the name of the card
    private boolean isFlipped;
    private boolean matched;

    public Card(int imageResourceId, String name) { // Modify the constructor to accept the name of the card
        this.imageResourceId = imageResourceId;
        this.name = name;
        this.isFlipped = false;
        this.matched = false;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public String getName() { // Add this method to get the name of the card
        return name;
    }

}