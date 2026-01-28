package cat.itacademy.s05.t01.blackjackv2.repository;

import cat.itacademy.s05.t01.blackjackv2.model.Player;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;


public interface PlayerRepository extends R2dbcRepository<Player, Long> {

    Flux<Player> findAllByOrderByGamesWonDesc();
}
