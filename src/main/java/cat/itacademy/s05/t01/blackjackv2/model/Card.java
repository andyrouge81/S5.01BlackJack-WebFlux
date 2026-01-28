package cat.itacademy.s05.t01.blackjackv2.model;

import cat.itacademy.s05.t01.blackjackv2.model.enums.Rank;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Suit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Card {

    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }


    public boolean isAce() {
        return rank.isAce();
    }

    public int getCardValue() {
        return rank.getCardValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
