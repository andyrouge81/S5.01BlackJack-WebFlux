package cat.itacademy.s05.t01.blackjackv2.repository;

import cat.itacademy.s05.t01.blackjackv2.model.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GameRepository extends ReactiveMongoRepository<Game, String> {
}
