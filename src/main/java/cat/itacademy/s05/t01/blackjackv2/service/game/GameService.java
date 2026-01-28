package cat.itacademy.s05.t01.blackjackv2.service.game;

import cat.itacademy.s05.t01.blackjackv2.model.Game;
import cat.itacademy.s05.t01.blackjackv2.model.enums.GameAction;
import reactor.core.publisher.Mono;

public interface GameService {

    Mono<Game> createGame(Long playerId);

    Mono<Game> findGameById(String gameId);

    Mono<Game> play(String gameId, GameAction action);

    Mono<Void> deleteGame(String gameId);


}
