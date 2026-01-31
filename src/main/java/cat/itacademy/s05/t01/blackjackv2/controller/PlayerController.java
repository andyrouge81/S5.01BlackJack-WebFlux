package cat.itacademy.s05.t01.blackjackv2.controller;

import cat.itacademy.s05.t01.blackjackv2.dto.request.CreatePlayerRequest;
import cat.itacademy.s05.t01.blackjackv2.dto.request.UpdatePlayerRequest;
import cat.itacademy.s05.t01.blackjackv2.dto.response.PlayerGameResponse;
import cat.itacademy.s05.t01.blackjackv2.dto.response.PlayerResponse;
import cat.itacademy.s05.t01.blackjackv2.mapper.PlayerMapper;
import cat.itacademy.s05.t01.blackjackv2.service.player.PlayerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Blackjack Player")
@RestController
@RequestMapping
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    public PlayerController(PlayerService playerService, PlayerMapper playerMapper) {

        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }
    @PostMapping("/player")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PlayerResponse> createPlayer(@Valid @RequestBody CreatePlayerRequest request) {
        return playerService.createPlayer(request.playerName())
                .map(playerMapper::toResponse);
    }
    @PutMapping("/player/{playerId}")
    public Mono<PlayerResponse> updatePlayerName(@PathVariable Long playerId,
                                         @Valid @RequestBody UpdatePlayerRequest request) {
        return playerService.updatePlayerName(playerId, request.playerName())
                .map(playerMapper::toResponse);
    }

    @GetMapping("/ranking")
    public Flux<PlayerResponse> getRanking() {

        return playerService.getRanking()
                .map(playerMapper::toResponse);
    }
    @GetMapping("player/{id}/games")
    public Mono<PlayerGameResponse> getPlayerGame(@PathVariable Long id){
        return playerService.getPlayerGames(id);
    }
}