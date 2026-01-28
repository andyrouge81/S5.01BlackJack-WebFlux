package cat.itacademy.s05.t01.blackjackv2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private  Deck deck;

    @BeforeEach
    void setup() {
        deck = new Deck();
    }

    @Test
    void givenADeck_shouldNotContains_DuplicateCards() {

        Set<Card> drawCards = new HashSet<>();

        for(int i = 0; i<52 ; i++){
            Card card = deck.drawCard();
            assertFalse(drawCards.contains(card), "Duplicate card found: "+ card);
            drawCards.add(card);
        }

        assertEquals(52, drawCards.size());
    }

    @Test
    void whenDrawACard_theReduceDeckSize() {

        deck.drawCard();

        int remaining = 0;
        while(true) {
            try {
                deck.drawCard();
                remaining++;
            } catch (IllegalStateException e) {
                break;
            }

        }
        assertEquals(51, remaining);

    }

    @Test
    void whenDrawMore52Cards_thenThrowException() {

        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        assertThrows(IllegalStateException.class, deck::drawCard);
    }

    @Test
    void whenShuffleDeck_thenNotChangeDeckSize() {

        deck.shuffleCards();

        int count = 0;
        while (true) {
            try {
                deck.drawCard();
                count++;

            } catch (IllegalStateException e) {
                break;
            }
        }
        assertEquals(52, count);
    }


}