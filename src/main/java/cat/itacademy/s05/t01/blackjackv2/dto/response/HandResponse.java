package cat.itacademy.s05.t01.blackjackv2.dto.response;

import java.util.List;

public record HandResponse(
        List<CardResponse> cards,
        Integer totalValue
) {
}
