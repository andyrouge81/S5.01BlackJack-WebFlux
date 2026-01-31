package cat.itacademy.s05.t01.blackjackv2.service.player;

import cat.itacademy.s05.t01.blackjackv2.exceptions.PlayerNotFoundException;
import cat.itacademy.s05.t01.blackjackv2.model.Game;
import cat.itacademy.s05.t01.blackjackv2.model.Player;
import cat.itacademy.s05.t01.blackjackv2.model.enums.GameAction;
import cat.itacademy.s05.t01.blackjackv2.model.enums.GameResult;
import cat.itacademy.s05.t01.blackjackv2.model.enums.GameStatus;
import cat.itacademy.s05.t01.blackjackv2.repository.GameRepository;
import cat.itacademy.s05.t01.blackjackv2.repository.PlayerRepository;
import cat.itacademy.s05.t01.blackjackv2.service.game.GameServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    GameRepository gameRepository;

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    GameServiceImpl gameService;

    @Test
    void whenGameFinishesAndPlayerWins_thenStatsAreUpdated() {

        Long playerId = 1L;
        String gameId = "game123";

        Player player = new Player("Andy");
        player.setId(playerId);
        player.setGamesPlayed(2);
        player.setGamesWon(1);

        Game game = new Game(playerId);
        game.setId(gameId);
        game.setStatus(GameStatus.IN_PROGRESS);

        Game finishedGame = new Game(playerId) {


            @Override
            public void dealerPlay() {

            }

            @Override
            public void determineWinner() {
                setResult(GameResult.PLAYER_WINS);
            }
        };
        finishedGame.setId(gameId);
        finishedGame.setStatus(GameStatus.IN_PROGRESS);

        when(gameRepository.findById(gameId)).thenReturn(Mono.just(finishedGame));
        when(playerRepository.findById(playerId)).thenReturn(Mono.just(player));
        when(playerRepository.save(any(Player.class))).thenAnswer(inv -> Mono.just(inv.getArgument(0)));
        when(gameRepository.save(any(Game.class))).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        Mono<Game> resultMono = gameService.play(gameId, GameAction.STAND);

        StepVerifier.create(resultMono)
                .expectNextMatches(g -> g.getResult() == GameResult.PLAYER_WINS)
                .verifyComplete();

        assertEquals(3, player.getGamesPlayed());
        assertEquals(2, player.getGamesWon());

        verify(playerRepository).save(player);
    }

    @Test
    void whenGameFinishesAndPlayerLoses_thenOnlyGamesPlayedIsUpdated() {

        Long playerId = 1L;
        String gameId = "game456";

        Player player = new Player("Andy");
        player.setId(playerId);
        player.setGamesPlayed(5);
        player.setGamesWon(2);

        Game finishedGame = new Game(playerId) {


            @Override
            public void dealerPlay() {

            }

            @Override
            public void determineWinner() {
                setResult(GameResult.DEALER_WINS);
            }
        };

        finishedGame.setId(gameId);
        finishedGame.setStatus(GameStatus.IN_PROGRESS);

        when(gameRepository.findById(gameId)).thenReturn(Mono.just(finishedGame));
        when(playerRepository.findById(playerId)).thenReturn(Mono.just(player));
        when(playerRepository.save(any(Player.class))).thenAnswer(inv -> Mono.just(inv.getArgument(0)));
        when(gameRepository.save(any(Game.class))).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        Mono<Game> resultMono = gameService.play(gameId, GameAction.STAND);

        StepVerifier.create(resultMono)
                .expectNextMatches(g -> g.getResult() == GameResult.DEALER_WINS)
                .verifyComplete();

        assertEquals(6, player.getGamesPlayed());
        assertEquals(2, player.getGamesWon());

        verify(playerRepository).save(player);
    }

    @Test
    void whenGameFinishesAndPlayerNotFound_thenErrorIsPropagated() {

        Long playerId = 99L;
        String gameId = "game789";

        Game finishedGame = new Game(playerId) {


            @Override
            public void dealerPlay() {

            }

            @Override
            public void determineWinner() {
                setResult(GameResult.PLAYER_WINS);
            }
        };

        finishedGame.setId(gameId);
        finishedGame.setStatus(GameStatus.IN_PROGRESS);

        when(gameRepository.findById(gameId)).thenReturn(Mono.just(finishedGame));
        when(playerRepository.findById(playerId)).thenReturn(Mono.empty());

        Mono<Game> resultMono = gameService.play(gameId, GameAction.STAND);

        StepVerifier.create(resultMono)
                .expectError(PlayerNotFoundException.class)
                .verify();

        verify(playerRepository, never()).save(any());
    }


}