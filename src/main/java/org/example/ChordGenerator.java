package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ChordGenerator {
    public static void main(String[] args) {

        /*
        While the original ChordGen simply randomly generated a chord from an array of notes and qualities,
        ChordGen2 improves upon that model by:
            (1) controlling movement between chords for a slightly more realistic progression
            (2) letting the user select between C#/Db and F#/Gb for a more consistent key
         */


        Scanner scanner = new Scanner(System.in);
        int noteNameIndex;
        int qualityIndex;
        int nextDistanceIndex;
        String chordName;
        Queue<String> nextFourChords = new LinkedList<>();

        String[] noteNames = new String[0];

        String[] qualities = {"", "7", "maj7", "m7", "dim7", "m7b5", "9", "maj9", "m9", "7#5", "7b5", "6", "6/9", "m6/9", "7b9", "m", "7#9",
                "7alt", "m6", "11", "7sus4", "13", "7#11", "add9", "min/maj7"};

        // This array allows for slightly more realistic movement between chords, while still allowing some curveballs
        // Multiples of certain numbers are to make that movement more likely
        int[] nextDistance = new int[]{1, 1, 2, 2, 5, 5, 5, 5, 5, 7, 7, 7, -3, -3, -2, -2, -1, -1, 3, 4, 6, 8};


        // This will only affect C#/Db and F#/Gb
        System.out.print("Excessive (s)harps or (f)lats? ");
        String keyChoice = scanner.nextLine();

        if (keyChoice.equals("s")) {

            noteNames = new String[]{"C", "C#", "D", "Eb", "E", "F", "F#", "G", "Ab", "A", "Bb", "B"};

        } else if (keyChoice.equals("f")) {

            noteNames = new String[]{"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};

        } else {
            System.out.print("Invalid input");
            System.exit(0);
        }

        System.out.print("Press enter to generate a chord, or enter q to quit. ");
        String userInput = scanner.nextLine();

        // Starting point, never prints
        noteNameIndex = (int)(Math.random() * noteNames.length);

        while (!userInput.equals("q")) {

            nextDistanceIndex = (int)(Math.random() * nextDistance.length);
            // For double checking
            // System.out.println("Next Distance: " + nextDistance[nextDistanceIndex]);

            qualityIndex = (int)(Math.random() * qualities.length);
            noteNameIndex += nextDistance[nextDistanceIndex];

            // This if statement should account for the index going out of bounds
            if (noteNameIndex >= noteNames.length) {
                noteNameIndex -= noteNames.length;
            } else if (noteNameIndex < 0) {
                noteNameIndex += noteNames.length;
            }
            // For double checking
            // System.out.println("NNI: " + noteNameIndex);

            chordName = noteNames[noteNameIndex] + qualities[qualityIndex];



            System.out.print(chordName + " ");
            userInput = scanner.nextLine();

        }

    }
}