package com.stir.cscu9t4practical1;

public class SprintEntry extends Entry{
    private int repetitions;
    private int recovery;

    public SprintEntry(String n, int d, int m, int y, int h, int min, int s, float dist, int repetitions, int recovery) {
        super(n, d, m, y, h, min, s, dist);
        this.repetitions = repetitions;
        this.recovery = recovery;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public int getRecovery() {
        return recovery;
    }
    public String getEntry () {
        String result = getName()+" sprinted "+getRepetitions()+ "x"+ String.format("%.0f", getDistance())+ "m in "
                +getHour()+":"+getMin()+":"+ getSec() + " with "+getRecovery()+ " minutes recovery on "
                +getDay()+"/"+getMonth()+"/"+getYear()+"\n";
        return result;

    }
}
