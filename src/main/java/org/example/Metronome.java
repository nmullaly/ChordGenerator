package org.example;

// based on code imported from rosettacode.org/wiki/Metronome
class Metronome{
    double bpm;
    int measureLength;
    int counter;
    public Metronome(double bpm, int measure){
        this.bpm = bpm;
        this.measureLength = measure;
    }
    public void start(){
        while(true){
            try {
                Thread.sleep((long)(1000*(60.0/bpm)));
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
            if (counter%measureLength==0){
                System.out.println("TICK");
            }else{
                System.out.println("TOCK");
            }
        }
    }

    public void countMeasure() {

        for (int i = 0; i < measureLength; i++) {

            System.out.print(" * ");

            try {
                Thread.sleep((long) (1000 * (60.0 / bpm)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println();

    }

}