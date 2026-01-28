package cat.itacademy.s05.t01.blackjackv2.mapper;


import cat.itacademy.s05.t01.blackjackv2.dto.response.CardResponse;
import cat.itacademy.s05.t01.blackjackv2.dto.response.GameResponse;
import cat.itacademy.s05.t01.blackjackv2.dto.response.HandResponse;
import cat.itacademy.s05.t01.blackjackv2.model.Card;
import cat.itacademy.s05.t01.blackjackv2.model.Game;
import cat.itacademy.s05.t01.blackjackv2.model.Hand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameMapper {

    public GameResponse toResponse(Game game) {

        return new GameResponse(
                game.getId(),
                game.getPlayerId(),
                toHandResponse(game.getPlayerHand()),
                toHandResponse(game.getDealerHand()),
                game.getResult()
        );
    }

    private HandResponse toHandResponse(Hand hand) {

        List<CardResponse> cards = hand.getCards().stream()
                .map(this::toCardResponse)
                .toList();

        return new HandResponse(cards, hand.calculateValue());
    }

    private CardResponse toCardResponse(Card card) {

        return new CardResponse(card.getSuit(), card.getRank());
    }

}
