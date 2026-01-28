package cat.itacademy.s05.t01.blackjackv2.service.player;

import cat.itacademy.s05.t01.blackjackv2.dto.response.PlayerGameResponse;
import cat.itacademy.s05.t01.blackjackv2.model.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerService {

    Mono<Player> createPlayer(String playerName);

    Mono<Player> updatePlayerName(Long playerId, String playerName);

    Mono<PlayerGameResponse> getPlayerGames(Long playerId);

    Flux<Player> getRanking();
}
