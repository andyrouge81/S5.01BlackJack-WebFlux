package cat.itacademy.s05.t01.blackjackv2.model;

import cat.itacademy.s05.t01.blackjackv2.model.enums.Rank;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {


    @Test
    void shouldBeIsBust() {
        Hand hand = new Hand();

        hand.addCard(new Card(Suit.CLUBS, Rank.QUEEN));
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.FOUR));

        assertTrue(hand.isBust());

    }

    @Test
    void shouldBeIsBlakJack() {
        Hand hand = new Hand();

        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));

        assertTrue(hand.isBlakJack());
        assertEquals(21, hand.calculateValue());
    }


}