package cat.itacademy.s05.t01.blackjackv2.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long playerId) {

        super("The player with id "+playerId+" not found");
    }
}
