package org.demo;

public class Stats {
    private int added;
    private int completed;
    private int spilled;

    public Stats(int added, int completed, int spilled) {
        this.added = added;
        this.completed = completed;
        this.spilled = spilled;
    }

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getSpilled() {
        return spilled;
    }

    public void setSpilled(int spilled) {
        this.spilled = spilled;
    }
}
