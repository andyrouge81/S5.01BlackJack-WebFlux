package cat.itacademy.s05.t01.blackjackv2.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdatePlayerRequest(
        @NotBlank(message = "Player name must not be blank")
        String playerName) {
}
