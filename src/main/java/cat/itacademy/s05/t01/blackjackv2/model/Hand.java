package cat.itacademy.s05.t01.blackjackv2.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hand {

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateValue() {
        int total = 0;
        int totalAces = 0;

        for(Card c : cards){
            total += c.getCardValue();
            if(c.isAce()) {
                totalAces++;
            }
        }

        while (totalAces > 0 && total +10 <= 21) {
            total += 10;
            totalAces--;

        }
        return total;
    }

    public boolean isBust() {
        return calculateValue() > 21;
    }

    public boolean isBlakJack() {
        return cards.size() == 2 && calculateValue() == 21;

    }


}

