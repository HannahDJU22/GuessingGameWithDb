package se.iths.guessinggamewithdb;

public class PlayerAverage {

    private String name;
    private double averageScore;

    public PlayerAverage(String name, double averageScore) {
        this.name = name;
        this.averageScore = averageScore;
    }

    public String getName() {
        return name;
    }

    public double getAverageScore() {
        return averageScore;
    }



}
