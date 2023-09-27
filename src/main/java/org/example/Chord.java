package org.example;

public class Chord {

    private String root;
    private String quality;

    private boolean usesSharps;

    private String[] commonQualities = {"", "m", "7", "maj7", "m7", "dim7", "m7b5", "6", "add9", "6/9"};
    private String[] uncommonQualities = {"9", "maj9", "m9", "7#5", "7b5", "m6/9", "7b9",
                                        "7#9", "7alt", "m6", "11", "7sus4", "13", "7#11", "min/maj7"};

    private String[] noteNames;

    public Chord(String root, String quality) {

        this.root = root;
        this.quality = quality;

    }

    public Chord(boolean usesSharps) {

        int rootIndex;
        int qualityIndex;
        String[] noteNames = new String[0];
        String[] qualities;

        if (Math.random() > .75) {
            qualities = uncommonQualities;
        } else {
            qualities = commonQualities;
        }

        if (usesSharps) {

            noteNames = new String[]{"C", "C#", "D", "Eb", "E", "F", "F#", "G", "Ab", "A", "Bb", "B"};

        } else {

            noteNames = new String[]{"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};

        }

        rootIndex = (int)(Math.random() * noteNames.length);
        qualityIndex = (int)(Math.random() * qualities.length);

        this.root = noteNames[rootIndex];
        this.quality = qualities[qualityIndex];

    }

    public String toString() {
        return root + quality;
    }



}
