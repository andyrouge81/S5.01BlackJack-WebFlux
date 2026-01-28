package cat.itacademy.s05.t01.blackjackv2.dto.response;

public record PlayerResponse(

        Long id,
        String playerName,
        Integer gamesPlayed,
        Integer gamesWon
) {
}
