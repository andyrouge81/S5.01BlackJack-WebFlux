package cat.itacademy.s05.t01.blackjackv2.dto.request;

import cat.itacademy.s05.t01.blackjackv2.model.enums.GameAction;

public record PlayRequest(GameAction action) {
}
