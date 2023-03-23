package se.iths.guessinggamewithdb;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Result {
    @Id
    @GeneratedValue
    private Long id;
   private int noOfGuesses;

    public Result(){}
    public Result(int noOfGuesses) {
        this.noOfGuesses = noOfGuesses;
    }

    public int getNoOfGuesses() {
        return noOfGuesses;
    }
    public void setNoOfGuesses(int noOfGuesses) {
        this.noOfGuesses = noOfGuesses;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
