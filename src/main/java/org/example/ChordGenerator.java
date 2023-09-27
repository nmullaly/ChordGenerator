package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ChordGenerator {
    public static void main(String[] args) {

        /*
        * ChordGenerator v3 updates:
        * (1) Implemented the Chord class to take all that ugly code out of my main method
        * (2) Added method to generate/print 3 upcoming chords after the current one
        * (3) Added method to cycle the array forward and add a new one
        *   - chose not to use a queue because I wanted to reuse them and it felt like unnecessary added complexity
        *
        * TODO:
        *  - reimplement probability based progressions, add common patterns
        *       - maybe make this a new progression class?
        *  - implement Key class to aid in logical-ish progressions
        *       - key changes
        *       - borrowed chords
        *  - Alter chord class to use 12-tone scale notation so it's transferable between keys
        *  - maybe : make a FourBars class and put the new v3 methods in there instead of main class?
        *  - and finally, of course, METRONOME
        *       - auto cycle chords
        *       - change time signature
        *       - change bpm
        *       - notate beats as " * " on the same line
        */


        Scanner scanner = new Scanner(System.in);

        // from older features
//        int noteNameIndex;
//        int qualityIndex;
//        int nextDistanceIndex;
//        String chordName;

        boolean usesSharps = false;
        Chord[] nextFourChords = new Chord[4];

        // This array allows for slightly more realistic movement between chords, while still allowing some curveballs
        // Multiples of certain numbers are to make that movement more likely
        // int[] nextDistance = new int[]{1, 1, 2, 2, 5, 5, 5, 5, 5, 7, 7, 7, -3, -3, -2, -2, -1, -1, 3, 4, 6, 8};

        // This will only affect C#/Db and F#/Gb
        System.out.print("Excessive (s)harps or (f)lats? ");
        String keyChoice = scanner.nextLine();

        boolean repeat = true;
        while (repeat) {

            if (keyChoice.equals("s")) {

                usesSharps = true;
                repeat = false;

            } else if (keyChoice.equals("f")) {

                usesSharps = false;
                repeat = false;

            } else {
                System.out.print("Invalid input");
            }

        }

        // Starts the queue with 4 random chords
        for (int i = 0; i < 4; i++) {
            nextFourChords[i] = new Chord(usesSharps);
        }



        System.out.print("Press enter to generate a chord, or enter q to quit. ");
        String userInput = scanner.nextLine();

        while (!userInput.equals("q")) {

            System.out.println(printChords(nextFourChords));
            nextFourChords = cycleChords(nextFourChords, usesSharps);

            System.out.print("Press enter to continue, or enter q to quit. ");
            userInput = scanner.nextLine();

        }


//        old version
//        // Starting point, never prints
//        noteNameIndex = (int)(Math.random() * noteNames.length);
//
//        while (!userInput.equals("q")) {
//
//            nextDistanceIndex = (int)(Math.random() * nextDistance.length);
//            // For double checking
//            // System.out.println("Next Distance: " + nextDistance[nextDistanceIndex]);
//
//            qualityIndex = (int)(Math.random() * qualities.length);
//            noteNameIndex += nextDistance[nextDistanceIndex];
//
//            // This if statement should account for the index going out of bounds
//            if (noteNameIndex >= noteNames.length) {
//                noteNameIndex -= noteNames.length;
//            } else if (noteNameIndex < 0) {
//                noteNameIndex += noteNames.length;
//            }
//            // For double checking
//            // System.out.println("NNI: " + noteNameIndex);
//
//            chordName = noteNames[noteNameIndex] + qualities[qualityIndex];
//
//
//
//            System.out.print(chordName + " ");
//            userInput = scanner.nextLine();
//
//        }

    }

    // prints the next chord, followed by 3 upcoming chords in parentheses
    private static String printChords(Chord[] chords) {

        String str = chords[0].toString() + " ( ";

        for (int i = 1; i < chords.length; i++) {
            str += chords[i].toString() + " ";
        }

        str += ")";

        return str;

    }

    // cycles the chords, removing the first one, moving up the next three, and adding a new random chord
    private static Chord[] cycleChords(Chord[] chords, boolean usesSharps) {

        for (int i = 1; i < chords.length; i++) {
            chords[i-1] = chords[i];
        }
        chords[chords.length - 1] = new Chord(usesSharps);

        return chords;

    }

}