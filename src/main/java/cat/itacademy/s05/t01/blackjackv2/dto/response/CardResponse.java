package cat.itacademy.s05.t01.blackjackv2.dto.response;

import cat.itacademy.s05.t01.blackjackv2.model.enums.Rank;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Suit;

public record CardResponse(
        Suit suit,
        Rank rank
) {
}
