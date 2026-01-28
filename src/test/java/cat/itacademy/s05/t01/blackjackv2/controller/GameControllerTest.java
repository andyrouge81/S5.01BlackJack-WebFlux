package cat.itacademy.s05.t01.blackjackv2.controller;

import cat.itacademy.s05.t01.blackjackv2.dto.request.CreateGameRequest;
import cat.itacademy.s05.t01.blackjackv2.dto.request.PlayRequest;
import cat.itacademy.s05.t01.blackjackv2.dto.response.GameResponse;
import cat.itacademy.s05.t01.blackjackv2.dto.response.HandResponse;
import cat.itacademy.s05.t01.blackjackv2.exceptions.GameNotFoundException;
import cat.itacademy.s05.t01.blackjackv2.exceptions.GlobalHandlerException;
import cat.itacademy.s05.t01.blackjackv2.mapper.GameMapper;
import cat.itacademy.s05.t01.blackjackv2.model.Game;
import cat.itacademy.s05.t01.blackjackv2.model.enums.GameAction;
import cat.itacademy.s05.t01.blackjackv2.service.game.GameServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(GameController.class)
@Import(GlobalHandlerException.class)
class GameControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    GameServiceImpl gameService;

    @MockBean
    GameMapper gameMapper;

    @Test
    void createGame_shouldReturn201() {
        Game game = new Game(1L);
        game.setId("g1");
        game.startGame();

        HandResponse playerHand = new HandResponse(List.of(), 15);
        HandResponse dealerHand = new HandResponse(List.of(), 12);

        GameResponse dto = new GameResponse(
                "g1",
                1L,
                playerHand,
                dealerHand,
                game.getResult()
        );

        when(gameService.createGame(1L)).thenReturn(Mono.just(game));
        when(gameMapper.toResponse(game)).thenReturn(dto);

        webTestClient.post()
                .uri("/game/new")
                .bodyValue(new CreateGameRequest(1L))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo("g1")
                .jsonPath("$.playerId").isEqualTo(1)
                .jsonPath("$.deck").doesNotExist();


    }

    @Test
    void getGameById_shouldReturnGame() {
        Game game = new Game(1L);
        game.setId("g1");


        HandResponse playerHand = new HandResponse(List.of(), 18);
        HandResponse dealerHand = new HandResponse(List.of(), 16);

        GameResponse dto = new GameResponse(
                "g1",
                1L,
                playerHand,
                dealerHand,
                game.getResult()
        );

        when(gameService.findGameById("g1")).thenReturn(Mono.just(game));
        when(gameMapper.toResponse(game)).thenReturn(dto);

        webTestClient.get()
                .uri("/game/g1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("g1")
                .jsonPath("$.playerHand.totalValue").isEqualTo(18)
                .jsonPath("$.dealerHand.totalValue").isEqualTo(16);
    }

    @Test
    void getGameById_whenNotFound_shouldReturn404AndApiError() {

        when(gameService.findGameById("bad-id"))
                .thenReturn(Mono.error(new GameNotFoundException("Game not found")));

        webTestClient.get()
                .uri("/game/bad-id")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.status").isEqualTo(404)
                .jsonPath("$.error").isEqualTo("Not Found");

    }

    @Test
    void play_shouldReturnUpdatedGame() {
        Game game = new Game(1L);
        game.setId("g1");

        HandResponse playerHand = new HandResponse(List.of(), 20);
        HandResponse dealerHand = new HandResponse(List.of(), 16);

        GameResponse dto = new GameResponse(
                "g1",
                1L,
                playerHand,
                dealerHand,
                game.getResult()
        );

        when(gameService.play("g1", GameAction.HIT)).thenReturn(Mono.just(game));
        when(gameMapper.toResponse(game)).thenReturn(dto);

        webTestClient.post()
                .uri("/game/g1/play")
                .bodyValue(new PlayRequest(GameAction.HIT))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("g1")
                .jsonPath("$.playerHand.totalValue").isEqualTo(20);
    }

    @Test
    void deleteGame_whenExists_shouldReturn204() {

        when(gameService.deleteGame("game1")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/game/game1/delete")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void deleteGame_whenNotFound_shouldReturn404() {

        when(gameService.deleteGame("bad-id"))
                .thenReturn(Mono.error(new GameNotFoundException("Game not found")));

        webTestClient.delete()
                .uri("/game/bad-id/delete")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.status").isEqualTo(404)
                .jsonPath("$.error").isEqualTo("Not Found");
    }
}