package cat.itacademy.s05.t01.blackjackv2.service;

import cat.itacademy.s05.t01.blackjackv2.model.Game;
import reactor.core.publisher.Mono;

public interface GameService {

    Mono<Game> createGame(Long playerId);

    Mono<Game> findGameById(String gameId);

    Mono<Game> play(String gameId);
}
