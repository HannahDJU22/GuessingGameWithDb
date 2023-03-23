package se.iths.guessinggamewithdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@SessionScope
public class GameService {
    int secretNumber;
    int countGuesses;
    Player player;
    boolean isLoggedin;
    List<String> listOfAnswers;

    public GameService() {
        newgame();
    }

    @Autowired PlayerRepository playerRepository;

    public void login(String playname) {
        if(isLoggedin) return;
        List<Player> playerList = playerRepository.findByName(playname);
        if(playerList.size()>0){
            player=playerList.get(0);
        }else{
            player=new Player(playname);
            player = playerRepository.save(player);
        }
        isLoggedin=true;
    }

    public void newgame(){
        Random randNo = new Random();
        secretNumber = randNo.nextInt(1, 101);
        listOfAnswers=new ArrayList<>();
        countGuesses=0;
    }

    public String getAnswer(int guessNo) {
        countGuesses++;

        if (guessNo < secretNumber) {
            listOfAnswers.add(guessNo + " is too low.");
            return " Try again";
        } else if (guessNo > secretNumber) {
            listOfAnswers.add(guessNo + " is too high.");
            return "Try again";
        } else {
            Player dbPlayer = playerRepository.findById(player.getId()).get();
            dbPlayer.addResult(new Result(countGuesses));
            playerRepository.save(dbPlayer);
            newgame();
            return "YOU WIN! " + guessNo + " was CORRECT. Please play the game again.";
        }

    }

    public List<String> getListOfAnswers() {
        return listOfAnswers;
    }
    public List<PlayerAverage> getTopList(){
        List<Player> playerList = playerRepository.findAll();
        List<PlayerAverage> averageScores = new ArrayList<>();
        for (Player p:playerList) {
            double avg = p.calcAvg();
            averageScores.add(new PlayerAverage(p.getName(), avg));
        }
        averageScores.sort((p1, p2)-> Double.compare(p1.getAverageScore(), p2.getAverageScore()));
        return averageScores;
    }
}
