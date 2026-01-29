package cat.itacademy.s05.t01.blackjackv2.dto.response;


import cat.itacademy.s05.t01.blackjackv2.model.enums.GameResult;


public record GameResponse (

        String id,
        Long playerId,
        HandResponse playerHand,
        HandResponse dealerHand,
        GameResult result

){ }
