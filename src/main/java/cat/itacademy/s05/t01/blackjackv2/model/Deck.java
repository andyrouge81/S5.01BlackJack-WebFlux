package cat.itacademy.s05.t01.blackjackv2.model;

import cat.itacademy.s05.t01.blackjackv2.model.enums.Rank;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Suit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Deck {

    private List<Card> cards = new ArrayList<>();

    public static Deck newDeck() {
        Deck deck = new Deck();
        deck.createDeck();
        deck.shuffleCards();
        return deck;

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
