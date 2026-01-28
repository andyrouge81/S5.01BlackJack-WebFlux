package cat.itacademy.s05.t01.blackjackv2.model;

import cat.itacademy.s05.t01.blackjackv2.model.enums.GameResult;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Rank;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setup() {
        game = new Game(1L);
    }

    @Test
    void whenPlayerBust_thenDealerWins() {

        game.getPlayerHand().addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
        game.getPlayerHand().addCard(new Card(Suit.CLUBS, Rank.KING));
        game.getPlayerHand().addCard(new Card(Suit.HEARTS, Rank.TWO));

        game.determineWinner();

        assertEquals(GameResult.DEALER_WINS, game.getResult());

    }

    @Test
    void whenDealerBust_thenPlayerWins() {

        game.getDealerHand().addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
        game.getDealerHand().addCard(new Card(Suit.CLUBS, Rank.KING));
        game.getDealerHand().addCard(new Card(Suit.HEARTS, Rank.TWO));

        game.determineWinner();

        assertEquals(GameResult.PLAYER_WINS, game.getResult());
    }

    @Test
    void whenDealerHasHigherScore_thenDealerWins() {

        game.getPlayerHand().addCard(new Card(Suit.HEARTS, Rank.EIGHT));
        game.getPlayerHand().addCard(new Card(Suit.CLUBS, Rank.NINE));

        game.getDealerHand().addCard(new Card(Suit.DIAMONDS, Rank.TEN));
        game.getDealerHand().addCard(new Card(Suit.SPADES, Rank.NINE));

        game.determineWinner();

        assertEquals(GameResult.DEALER_WINS, game.getResult());
    }

    @Test
    void whenSameScore_thenPush() {

        game.getPlayerHand().addCard(new Card(Suit.HEARTS, Rank.TEN));
        game.getPlayerHand().addCard(new Card(Suit.CLUBS, Rank.NINE));

        game.getDealerHand().addCard(new Card(Suit.DIAMONDS, Rank.TEN));
        game.getDealerHand().addCard(new Card(Suit.SPADES, Rank.NINE));

        game.determineWinner();

        assertEquals(GameResult.DRAW, game.getResult());
    }

    @Test
    void whenPlayerHasBlackjack_thenPlayerWins() {

        game.getPlayerHand().addCard(new Card(Suit.HEARTS, Rank.ACE));
        game.getPlayerHand().addCard(new Card(Suit.CLUBS, Rank.KING));

        game.getDealerHand().addCard(new Card(Suit.DIAMONDS, Rank.NINE));
        game.getDealerHand().addCard(new Card(Suit.SPADES, Rank.SEVEN));

        game.determineWinner();

        assertEquals(GameResult.PLAYER_WINS, game.getResult());

    }

    @Test
    void dealerBlackjackBeatsLowerPlayerScore() {

        game.getDealerHand().addCard(new Card(Suit.HEARTS, Rank.ACE));
        game.getDealerHand().addCard(new Card(Suit.CLUBS, Rank.KING)); // 21

        game.getPlayerHand().addCard(new Card(Suit.DIAMONDS, Rank.TEN));
        game.getPlayerHand().addCard(new Card(Suit.SPADES, Rank.QUEEN)); // 20

        game.determineWinner();

        assertEquals(GameResult.DEALER_WINS, game.getResult());
    }

    @Test
    void bothHave21_thenDraw() {

        game.getPlayerHand().addCard(new Card(Suit.HEARTS, Rank.ACE));
        game.getPlayerHand().addCard(new Card(Suit.CLUBS, Rank.KING));

        game.getDealerHand().addCard(new Card(Suit.DIAMONDS, Rank.ACE));
        game.getDealerHand().addCard(new Card(Suit.SPADES, Rank.KING));

        game.determineWinner();

        assertEquals(GameResult.DRAW, game.getResult());

    }

}