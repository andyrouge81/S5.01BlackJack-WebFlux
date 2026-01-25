package cat.itacademy.s05.t01.blackjackv2.model;

import cat.itacademy.s05.t01.blackjackv2.model.enums.Rank;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Suit;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        createDeck();
        shuffleCards();
    }

    private void createDeck() {
        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if(cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.remove(0);
    }
}
