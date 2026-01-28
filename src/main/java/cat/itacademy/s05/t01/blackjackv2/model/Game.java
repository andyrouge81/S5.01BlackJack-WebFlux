package cat.itacademy.s05.t01.blackjackv2.model;


import cat.itacademy.s05.t01.blackjackv2.model.enums.GameResult;
import cat.itacademy.s05.t01.blackjackv2.model.enums.GameStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "games")
public class Game {

    @Id
    private String id;

    private Long playerId;

    private Hand playerHand;
    private Hand dealerHand;

    @JsonIgnore
    private Deck deck;

    private GameStatus status;
    private GameResult result;

    public Game(Long playerId) {
        this.playerId = playerId;
        this.deck = Deck.newDeck();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.status = GameStatus.CREATED;
    }

    public void startGame() {
        deck.shuffleCards();

        playerHit();
        dealerPlay();
        playerHit();
        dealerPlay();


        if(playerHand.isBlakJack() || dealerHand.isBlakJack()) {
            this.status = GameStatus.FINISHED;
            determineWinner();
        } else {
            status = GameStatus.IN_PROGRESS;
        }
    }

    //HIT
    public void playerHit() {
        if(status != GameStatus.IN_PROGRESS) {
            return;
        }

        if(playerHand.isBlakJack() || playerHand.isBust()) {
            return;
        }

        playerHand.addCard(deck.drawCard());

        if(playerHand.isBust() || playerHand.isBlakJack()) {
            status = GameStatus.FINISHED;
        }
    }


    public void dealerPlay() {
        if(status != GameStatus.IN_PROGRESS){
            return;
        }

        while(dealerHand.calculateValue() < 17) {
            dealerHand.addCard(deck.drawCard());
        }

        status = GameStatus.FINISHED;

    }

    public void determineWinner() {
        int playerValue = playerHand.calculateValue();
        int dealerValue = dealerHand.calculateValue();

        if(playerHand.isBust()) {
            result = GameResult.DEALER_WINS;
            return;
        }

        if(dealerHand.isBust()) {
            result = GameResult.PLAYER_WINS;
            return;
        }

        if(playerValue > dealerValue) {
            result = GameResult.PLAYER_WINS;
        } else if (dealerValue > playerValue) {
            result = GameResult.DEALER_WINS;
        } else {
            result = GameResult.DRAW;
        }
    }


}
