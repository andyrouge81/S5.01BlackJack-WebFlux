package cat.itacademy.s05.t01.blackjackv2.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String gameId) {

        super("Game with id "+ gameId+ " not found");
    }
}
