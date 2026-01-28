package cat.itacademy.s05.t01.blackjackv2.service;

import cat.itacademy.s05.t01.blackjackv2.exceptions.GameNotFoundException;
import cat.itacademy.s05.t01.blackjackv2.exceptions.PlayerNotFoundException;
import cat.itacademy.s05.t01.blackjackv2.model.Game;
import cat.itacademy.s05.t01.blackjackv2.model.enums.GameAction;
import cat.itacademy.s05.t01.blackjackv2.model.enums.GameStatus;
import cat.itacademy.s05.t01.blackjackv2.repository.GameRepository;
import cat.itacademy.s05.t01.blackjackv2.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameServiceImpl(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Mono<Game> createGame(Long playerId) {
        return playerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(playerId)))
                .flatMap(player->{
                    Game game = new Game(playerId);
                    game.startGame();
                    return gameRepository.save(game);
                });
    }

    @Override
    public Mono<Game> findGameById(String gameId) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)));
    }

    @Override
    public Mono<Game> play(String gameId, GameAction action) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)))
                .flatMap(game -> {
                    if (game.getStatus() != GameStatus.IN_PROGRESS) {
                        return Mono.error(new IllegalStateException("Game is not in progress"));

                    }

                    switch (action) {
                        case HIT -> game.playerHit();
                        case STAND -> {
                            game.playerStand();
                            game.dealerPlay();
                        }
                    }

                    if (game.getStatus() == GameStatus.FINISHED) {
                        game.determineWinner();
                    }

                    return gameRepository.save(game);
                });
    }

    @Override
    public Mono<Void> deleteGame(String gameId) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)))
                .flatMap(game -> gameRepository.delete(game));
    }


}
