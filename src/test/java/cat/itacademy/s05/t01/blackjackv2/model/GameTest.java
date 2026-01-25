package cat.itacademy.s05.t01.blackjackv2.model;

import cat.itacademy.s05.t01.blackjackv2.model.enums.GameResult;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Rank;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void whenPlayerBust_thenDealerWins() {

        Game game = new Game(1L);

        game.getPlayerHand().addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
        game.getPlayerHand().addCard(new Card(Suit.CLUBS, Rank.KING));
        game.getPlayerHand().addCard(new Card(Suit.HEARTS, Rank.TWO));

        game.determineWinner();

        assertEquals(GameResult.DEALER_WINS, game.getResult());

    }

}