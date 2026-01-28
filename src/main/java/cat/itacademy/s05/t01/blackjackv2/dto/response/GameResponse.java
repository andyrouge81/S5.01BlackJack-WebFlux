package cat.itacademy.s05.t01.blackjackv2.dto.response;


import cat.itacademy.s05.t01.blackjackv2.model.enums.GameResult;
import cat.itacademy.s05.t01.blackjackv2.model.enums.GameStatus;

public record GameResponse (

        String id,
        Long playerId,
        HandResponse playerHand,
        HandResponse dealerHand,
        GameStatus status,
        GameResult result

){ }
