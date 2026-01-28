package cat.itacademy.s05.t01.blackjackv2.dto;

import java.time.Instant;

public record ApiError(
        int status,
        String error,
        String message,
        Instant timestamp
) {
}
