package cat.itacademy.s05.t01.blackjackv2.service.player;

import cat.itacademy.s05.t01.blackjackv2.dto.response.PlayerGameResponse;
import cat.itacademy.s05.t01.blackjackv2.model.Player;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerService {

    Mono<Player> createPlayer(String playerName, Role role);

    Mono<Player> updatePlayerName(Long playerId, String playerName);

    Mono<PlayerGameResponse> getPlayerGames(Long playerId);

    Flux<Player> getRanking();
}
