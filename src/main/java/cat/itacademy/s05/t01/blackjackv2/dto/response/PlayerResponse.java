package cat.itacademy.s05.t01.blackjackv2.dto.response;

import cat.itacademy.s05.t01.blackjackv2.model.enums.Role;

public record PlayerResponse(

        Long id,
        String playerName,
        Role role,
        Integer gamesPlayed,
        Integer gamesWon
) {
}
