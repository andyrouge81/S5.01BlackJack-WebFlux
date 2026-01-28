package cat.itacademy.s05.t01.blackjackv2.model;

import cat.itacademy.s05.t01.blackjackv2.model.enums.Rank;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setup() {
        hand = new Hand();
    }

    @Test
    void shouldBeIsBust() {


        hand.addCard(new Card(Suit.CLUBS, Rank.QUEEN));
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.FOUR));

        assertTrue(hand.isBust());

    }

    @Test
    void shouldBeIsBlakJack() {

        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));

        assertTrue(hand.isBlakJack());
        assertEquals(21, hand.calculateValue());
    }

    @Test
    void whenEmptyHand_shouldValueIsZero() {

        assertEquals(0, hand.calculateValue());
    }

    @Test
    void givenTwoCards_whenSumNumber_thenResultIsCorrect() {

        hand.addCard(new Card(Suit.HEARTS, Rank.FIVE));
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));

        assertEquals(15, hand.calculateValue());
    }

    @Test
    void givenFaceCards_thenWorthShouldBeTen() {
        hand.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
        hand.addCard(new Card(Suit.HEARTS, Rank.JACK));

        assertEquals(20 , hand.calculateValue());

    }

    @Test
    void givenAce_whenNoRisk_thenAceWorthEleven() {

        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.SEVEN));

        assertEquals(18, hand.calculateValue());


    }

    @Test
    void givenAce_whenRisk_thenAceWorthOne() {

        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));

        assertEquals(21, hand.calculateValue());
    }

    @Test
    void givenMultipleAces_shouldAdjustCorrectly() {

        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.NINE));

        assertEquals(21, hand.calculateValue());
    }

    @Test
    void givenThreeCards_whenSumIs21_thenIsNotBlackjack() {

        hand.addCard(new Card(Suit.CLUBS, Rank.SEVEN));
        hand.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.SEVEN));

        assertEquals(21, hand.calculateValue());
        assertFalse(hand.isBlakJack());
    }


}