package cat.itacademy.s05.t01.blackjackv2.model;

import cat.itacademy.s05.t01.blackjackv2.model.enums.Rank;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void givenACard_thenShouldStoreSuitAndRank() {

        Card card = new Card(Suit.HEARTS, Rank.ACE);

        assertEquals(Suit.HEARTS, card.getSuit());
        assertEquals(Rank.ACE, card.getRank());
    }

    @Test
    void givenTwoCardsWithSameSuitAndRank_thenShouldBeEqual() {

        Card card1 = new Card(Suit.SPADES, Rank.KING);
        Card card2 = new Card(Suit.SPADES, Rank.KING);

        assertEquals(card1, card2);
    }

    @Test
    void givenThreeDifferentCards_thenShouldNotBeEqual() {
        Card card1 = new Card(Suit.SPADES, Rank.KING);
        Card card2 = new Card(Suit.HEARTS, Rank.KING);
        Card card3 = new Card(Suit.SPADES, Rank.QUEEN);

        assertNotEquals(card1, card2);
        assertNotEquals(card1, card3);
    }

    @Test
    void givenTwoEqualCards_thenShouldHaveSameHashCode() {
        Card card1 = new Card(Suit.DIAMONDS, Rank.TEN);
        Card card2 = new Card(Suit.DIAMONDS, Rank.TEN);

        assertEquals(card1.hashCode(), card2.hashCode());
    }

}