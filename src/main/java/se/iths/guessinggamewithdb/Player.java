package se.iths.guessinggamewithdb;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Result> playerScores = new ArrayList<>();

    public Player(){}
    public Player(String name) {
        this.name = name;
    }

    public void addResult(Result result) {
        playerScores.add(result);
    }

    public double calcAvg() {
        double sum = 0;
        for (Result r:playerScores) {
            sum+= r.getNoOfGuesses();
        }
        return sum/playerScores.size();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
