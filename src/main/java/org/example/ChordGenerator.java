package org.example;

import java.util.Scanner;

public class ChordGenerator {
    public static void main(String[] args) {

        /*
        *
        * Start the program by opening the jar folder in terminal and typing java -jar ChordGenerator.jar
        *
        * ChordGenerator v3 updates:
        * (1) Implemented the Chord class to take all that ugly code out of my main method
        * (2) Added method to generate/print 3 upcoming chords after the current one
        * (3) Added method to cycle the array forward and add a new one
        *   - chose not to use a queue because I wanted to reuse them and it felt like unnecessary added complexity
        *
        * ChordGenerator v4 updates:
        * (1) Implemented Metronome class (thx rosetta code <3), with bpm and measure length set by user
        * (2) Removed obsolete code
        * (3) Removed input checking for sharps/flats because I don't know how to do it for numbers yet so it felt a little pointless
        *
        * v5 updates:
        * (1) Removed boolean usesSharps in favor of probability based switching between C#/Db and F#/Gb
        * (2) cycleChords is now cycleFourChords and works specifically on a four chord array
        *   - because there is probably no reason to change it and it wouldn't be too hard if I needed to
        * (3) Random Chord constructor based on the root of the previous chord
        * (4) Blank Chord constructor
        * (5) Metronome now counts off one measure before Chords print
        * (6) Changed bpm data type to int because why was it double in the first place who even cares that much
        * (7) PROBABILITY BASED MOVEMENT YAYYY!!!
        *
        *
        * TODO:
        *  - Exception catching!
        *  - Add common patterns such as ii-V-I, moving stepwise
        *  - change nextFourChords to an ArrayList
        *  - difficulty settings (common extensions, common/uncommon extensions, uncommon only)
        *  Down the line:
        *  - Metronome sound effect
        *  - Play bass note
        *  - toggle sounds
        *  - Key class for more tonal progressions
        *  - Set # of measures, loop when done
        *  Once I learn front end:
        *  - Start/stop button instead of infinite loop
        *  - Edit fields for tempo / measure length
        */


        Scanner scanner = new Scanner(System.in);

        int bpm;
        int measureLength;
        Chord[] nextFourChords = new Chord[4];
        nextFourChords[0] = new Chord();

        System.out.print("BPM: ");
        bpm = scanner.nextInt();

        System.out.print("Beats in a measure: ");
        measureLength = scanner.nextInt();

        Metronome metronome = new Metronome(bpm, measureLength);

        // Starts the queue with 4 random chords
        for (int i = 1; i < 4; i++) {
            nextFourChords[i] = new Chord(nextFourChords[i-1].getRootNumber());
        }

        System.out.println("The program will run automatically. Close the window to stop.");
        System.out.println("Don't forget to have fun :^)");
        System.out.println();

        while (true) {

            metronome.countMeasure();
            System.out.println(printChords(nextFourChords));
            nextFourChords = cycleFourChords(nextFourChords);

        }

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

    // cycles the chords, removing the first one, moving up the next three, and adding a newly generated chord
    private static Chord[] cycleFourChords(Chord[] chords) {

        for (int i = 1; i < chords.length; i++) {
            chords[i-1] = chords[i];
        }
        chords[3] = new Chord(chords[2].getRootNumber());

        return chords;

    }

}