package cat.itacademy.s05.t01.blackjackv2.service.player;

import cat.itacademy.s05.t01.blackjackv2.dto.response.PlayerGameResponse;
import cat.itacademy.s05.t01.blackjackv2.exceptions.PlayerNotFoundException;
import cat.itacademy.s05.t01.blackjackv2.model.Game;
import cat.itacademy.s05.t01.blackjackv2.model.Player;
import cat.itacademy.s05.t01.blackjackv2.repository.GameRepository;
import cat.itacademy.s05.t01.blackjackv2.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayerServiceImpl implements  PlayerService{

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    public PlayerServiceImpl(PlayerRepository playerRepository, GameRepository gameRepository) {

        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Mono<Player> createPlayer(String playerName) {
        Player player = new Player(playerName);
        return playerRepository.save(player);
    }


    @Override
    public Mono<Player> updatePlayerName (Long playerId, String playerName) {
        return playerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(playerId)))
                .flatMap(player -> {
                    player.setPlayerName(playerName);
                    return playerRepository.save(player);
                });
    }

    @Override
    public Mono<PlayerGameResponse> getPlayerGames(Long playerId){
        return playerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(playerId)))
                .flatMap(player ->
                        gameRepository.findByPlayerId(playerId)
                .map(Game::getId)
                .collectList()
                .map(gameIds -> new PlayerGameResponse(
                        player.getId(),
                        player.getPlayerName(),
                        gameIds
                )));
    }

    @Override
    public Flux<Player> getRanking() {
        return playerRepository.findAllByOrderByGamesWonDesc();
    }


}
