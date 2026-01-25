package cat.itacademy.s05.t01.blackjackv2.controller;

import cat.itacademy.s05.t01.blackjackv2.dto.CreateGameRequest;
import cat.itacademy.s05.t01.blackjackv2.model.Game;
import cat.itacademy.s05.t01.blackjackv2.service.GameServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameServiceImpl gameService;

    public GameController(GameServiceImpl gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new")
    public Mono<ResponseEntity<Game>> createGame(@RequestBody CreateGameRequest request) {
        return gameService.createGame(request.playerId())
                .map(game->
                    ResponseEntity.status(HttpStatus.CREATED).body(game));

    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Game>> getGameById(@PathVariable String gameId) {
        return gameService.findGameById(gameId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/play")
    public Mono<ResponseEntity<Game>> play(@PathVariable String id) {
        return gameService.play(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
