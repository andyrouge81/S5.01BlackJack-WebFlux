package cat.itacademy.s05.t01.blackjackv2.service;

import cat.itacademy.s05.t01.blackjackv2.model.Game;
import cat.itacademy.s05.t01.blackjackv2.model.enums.GameStatus;
import cat.itacademy.s05.t01.blackjackv2.repository.GameRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Mono<Game> createGame(Long playerId) {

        Game game = new Game(playerId);
        game.startGame();

        return gameRepository.save(game);
    }

    @Override
    public Mono<Game> findGameById(String gameId) {
        return gameRepository.findById(gameId);
    }

    @Override
    public Mono<Game> play(String gameId) {
        return gameRepository.findById(gameId)
                .map(game ->{
                    game.playerHit();
                    if(game.getStatus() == GameStatus.FINISHED) {
                        game.determineWinner();
                    }
                    return game;
                })
                .flatMap(gameRepository::save);
    }
}
