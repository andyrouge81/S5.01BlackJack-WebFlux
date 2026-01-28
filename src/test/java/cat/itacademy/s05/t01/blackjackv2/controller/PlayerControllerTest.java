package cat.itacademy.s05.t01.blackjackv2.controller;

import cat.itacademy.s05.t01.blackjackv2.dto.request.UpdatePlayerRequest;
import cat.itacademy.s05.t01.blackjackv2.dto.response.PlayerResponse;
import cat.itacademy.s05.t01.blackjackv2.exceptions.GlobalHandlerException;
import cat.itacademy.s05.t01.blackjackv2.exceptions.PlayerNotFoundException;
import cat.itacademy.s05.t01.blackjackv2.mapper.PlayerMapper;
import cat.itacademy.s05.t01.blackjackv2.model.Player;
import cat.itacademy.s05.t01.blackjackv2.service.player.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;


@WebFluxTest(PlayerController.class)
@Import(GlobalHandlerException.class)
class PlayerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    PlayerServiceImpl playerService;

    @MockBean
    PlayerMapper playerMapper;

    @Test
    void updatePlayerName_shouldReturnUpdatedPlayer() {
        Player player = new Player("Alex", null);
        player.setId(1L);

        PlayerResponse dto = new PlayerResponse(
                1L,
                "AlexUpdated",
                null,
                null,
                null
        );

        when(playerService.updatePlayerName(1L, "AlexUpdated"))
                .thenReturn(Mono.just(player));

        when(playerMapper.toResponse(player)).thenReturn(dto);

        webTestClient.put()
                .uri("/player/1")
                .bodyValue(new UpdatePlayerRequest("AlexUpdated"))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.playerName").isEqualTo("AlexUpdated")
                .jsonPath("$.hand").doesNotExist();
    }

    @Test
    void updatePlayerName_whenNotFound_shouldReturn404() {
        when(playerService.updatePlayerName(99L, "Ghost"))
                .thenReturn(Mono.error(new PlayerNotFoundException(99L)));

        webTestClient.put()
                .uri("/player/99")
                .bodyValue(new UpdatePlayerRequest("Ghost"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.status").isEqualTo(404);
    }

    @Test
    void getRanking_shouldReturnPlayers() {
        Player p1 = new Player("Alice", null);
        p1.setId(1L);
        p1.setGamesWon(5);

        Player p2 = new Player("Bob", null);
        p2.setId(2L);
        p2.setGamesWon(2);

        PlayerResponse dto1 = new PlayerResponse(1L, "Alice", null, null, 5);
        PlayerResponse dto2 = new PlayerResponse(2L, "Bob", null, null, 2);

        when(playerService.getRanking()).thenReturn(Flux.just(p1, p2));
        when(playerMapper.toResponse(p1)).thenReturn(dto1);
        when(playerMapper.toResponse(p2)).thenReturn(dto2);

        webTestClient.get()
                .uri("/ranking")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].playerName").isEqualTo("Alice")
                .jsonPath("$[1].playerName").isEqualTo("Bob");


    }

}