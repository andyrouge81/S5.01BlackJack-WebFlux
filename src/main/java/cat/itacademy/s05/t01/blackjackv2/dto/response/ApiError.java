package cat.itacademy.s05.t01.blackjackv2.dto.response;

import java.time.Instant;

public record ApiError(
        int status,
        String error,
        String message,
        Instant timestamp
) {
}
