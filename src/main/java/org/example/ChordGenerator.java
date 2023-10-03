package org.example;

import java.util.Scanner;

public class ChordGenerator {
    public static void main(String[] args) {

        /*
        *
        * For TE ppl: start the program by opening the jar folder in terminal and typing java -jar ChordGenerator.jar
        * because for some reason this computer can't just open a jar :/
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
        * TODO:
        *  - Reimplement probability based progressions, add common patterns
        *  - Alter chord class to use 12-tone scale notation so it's transferable between keys
        *  - Make Map of 12-tone numbers to letter names
        *       - 50/50 chance for C#/Db and F#/Gb
        *  - change nextFourChords to an ArrayList
        *  - add a count off before chords print
        *  - difficulty settings (common extensions, common/uncommon extensions, uncommon only)
        *  Down the line:
        *  - Key class for more tonal progressions
        *  - Set # of measures, loop when done
        *  Once I learn frontend:
        *  - Start/stop button
        *  - Edit fields for tempo / measure length
        */


        Scanner scanner = new Scanner(System.in);

        double bpm;
        int measureLength;
        boolean usesSharps = false;
        Chord[] nextFourChords = new Chord[4];

        // I wrote the next 3 lines for v2 before I remembered that switch statements exist but I'm keeping them so I can remember the probability I decided on
        // This array allows for slightly more realistic movement between chords, while still allowing some random movement
        // Multiples of certain numbers are to make that movement more likely
        // int[] nextDistance = new int[]{1, 1, 2, 2, 5, 5, 5, 5, 5, 7, 7, 7, -3, -3, -2, -2, -1, -1, 3, 4, 6, 8};

        // This will only affect C#/Db and F#/Gb
        System.out.print("Excessive (s)harps or (f)lats? ");
        String keyChoice = scanner.nextLine();

        System.out.print("BPM: ");
        bpm = scanner.nextDouble();

        System.out.print("Beats in a measure: ");
        measureLength = scanner.nextInt();

        Metronome metronome = new Metronome(bpm, measureLength);

        // Starts the queue with 4 random chords
        for (int i = 0; i < 4; i++) {
            nextFourChords[i] = new Chord(usesSharps);
        }

        System.out.println("The program will run automatically. Close the window to stop.");
        System.out.println("Don't forget to have fun :^)");
        String userInput = scanner.nextLine();

        while (true) {

            System.out.println(printChords(nextFourChords));
            metronome.countMeasure();
            nextFourChords = cycleChords(nextFourChords, usesSharps);

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

    // cycles the chords, removing the first one, moving up the next three, and adding a new random chord
    private static Chord[] cycleChords(Chord[] chords, boolean usesSharps) {

        for (int i = 1; i < chords.length; i++) {
            chords[i-1] = chords[i];
        }
        chords[chords.length - 1] = new Chord(usesSharps);

        return chords;

    }

}