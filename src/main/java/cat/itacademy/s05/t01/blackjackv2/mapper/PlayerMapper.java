package cat.itacademy.s05.t01.blackjackv2.mapper;

import cat.itacademy.s05.t01.blackjackv2.dto.response.PlayerResponse;
import cat.itacademy.s05.t01.blackjackv2.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerResponse toResponse(Player player) {
        return new PlayerResponse(
                player.getId(),
                player.getPlayerName(),
                player.getRole(),
                player.getGamesPlayed(),
                player.getGamesWon()
        );
    }
}
