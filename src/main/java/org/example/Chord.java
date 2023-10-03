package org.example;

public class Chord {

    private int rootNumber;
    private String rootLetter;
    private String quality;

    private String[] letterNames = {"C", null, "D", "Eb", "E", "F", null, "G", "Ab", "A", "Bb", "B"};
    private String[] commonQualities = {"", "m", "7", "maj7", "m7", "dim7", "m7b5", "6", "add9", "6/9"};
    private String[] uncommonQualities = {"9", "maj9", "m9", "7#5", "7b5", "m6/9", "7b9",
                                        "7#9", "7alt", "m6", "11", "7sus4", "13", "7#11", "min/maj7"};

    public Chord() {

        int rootIndex;
        int qualityIndex;
        String[] qualities;

        if (Math.random() > .75) {
            qualities = uncommonQualities;
        } else {
            qualities = commonQualities;
        }

        qualityIndex = (int)(Math.random() * qualities.length);
        this.quality = qualities[qualityIndex];


        rootIndex = (int)(Math.random() * letterNames.length);
        this.rootNumber = rootIndex;

        if (rootIndex == 1) {

            if (Math.random() < .5) {
                this.rootLetter = "C#";
            } else {
                this.rootLetter = "Db";
            }

        } else if (rootIndex == 6) {

            if (Math.random() < .5) {
                this.rootLetter = "F#";
            } else {
                this.rootLetter = "Gb";
            }

        } else {
            this.rootLetter = letterNames[rootIndex];
        }

    }

    public Chord(int rootNumber, String quality) {

        this.rootNumber = rootNumber;
        this.quality = quality;

    }

    public Chord(int lastRoot) {

        int rootIndex;
        int qualityIndex;
        int rootMovement;
        String[] qualities;

        // this block picks a distance from last root to next and assigns the according letter name
        // i just blocked it so it's more easily distinguishable
        // i don't feel great about this 12 layer if statement but I don't think there's another way to deal with probability
        {
            double randomNumber = Math.random();
            if (randomNumber < .1) {
                rootMovement = 0;
            } else if (randomNumber < .2) {
                rootMovement = 1;
            } else if (randomNumber < .3) {
                rootMovement = 2;
            } else if (randomNumber < .35) {
                rootMovement = 3;
            } else if (randomNumber < .4) {
                rootMovement = 4;
            } else if (randomNumber < .6) {
                rootMovement = 5;
            } else if (randomNumber < .65) {
                rootMovement = 6;
            } else if (randomNumber < .7) {
                rootMovement = 7;
            } else if (randomNumber < .75) {
                rootMovement = 8;
            } else if (randomNumber < .8) {
                rootMovement = 9;
            } else if (randomNumber < .9) {
                rootMovement = 10;
            } else {
                rootMovement = 11;
            }

            rootIndex = lastRoot + rootMovement;
            while (rootIndex > letterNames.length) {
                rootIndex -= letterNames.length;
            }

            if (rootIndex == 1) {

                if (Math.random() < .5) {
                    this.rootLetter = "C#";
                } else {
                    this.rootLetter = "Db";
                }

            } else if (rootIndex == 6) {

                if (Math.random() < .5) {
                    this.rootLetter = "F#";
                } else {
                    this.rootLetter = "Gb";
                }

            } else {
                this.rootLetter = letterNames[rootIndex];
            }
        }

        // this block decides the chord quality
        {
            if (Math.random() > .75) {
                qualities = uncommonQualities;
            } else {
                qualities = commonQualities;
            }
            qualityIndex = (int) (Math.random() * qualities.length);

            this.quality = qualities[qualityIndex];
        }

    }

    public int getRootNumber() {
        return this.rootNumber;
    }

    public String toString() {
        return this.rootLetter + this.quality;
    }



}
