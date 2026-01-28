package cat.itacademy.s05.t01.blackjackv2.dto.response;

import java.util.List;

public record PlayerGameResponse(
        Long playerId,
        String playerName,
        List<String> gamesIds
) { }
