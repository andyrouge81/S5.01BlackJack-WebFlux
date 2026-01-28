package cat.itacademy.s05.t01.blackjackv2.controller;

import cat.itacademy.s05.t01.blackjackv2.dto.request.CreateGameRequest;
import cat.itacademy.s05.t01.blackjackv2.dto.request.PlayRequest;
import cat.itacademy.s05.t01.blackjackv2.dto.response.GameResponse;
import cat.itacademy.s05.t01.blackjackv2.mapper.GameMapper;
import cat.itacademy.s05.t01.blackjackv2.service.game.GameService;
import cat.itacademy.s05.t01.blackjackv2.service.game.GameServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Blackjack Game")
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final GameMapper mapper;

    public GameController(GameServiceImpl gameService, GameMapper mapper) {

        this.gameService = gameService;
        this.mapper = mapper;
    }

    @PostMapping("/new")
    public Mono<ResponseEntity<GameResponse>> createGame(@RequestBody CreateGameRequest request) {

        return gameService.createGame(request.playerId())
                .map(mapper::toResponse)
                .map(dto ->
                    ResponseEntity.status(HttpStatus.CREATED).body(dto));

    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<GameResponse>> getGameById(@PathVariable("id") String gameId) {
        return gameService.findGameById(gameId)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/{id}/play")
    @ResponseStatus(HttpStatus.OK)
    public Mono<GameResponse> play(@PathVariable("id") String id, @RequestBody PlayRequest request) {
        return gameService.play(id, request.action())
                .map(mapper::toResponse);

    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteGame(@PathVariable("id") String gameId) {
        return gameService.deleteGame(gameId);
    }
}
