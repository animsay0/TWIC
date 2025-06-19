package fr.eseo.twic.alidouya.server.dto;

import java.time.Instant;

public record OrderDTO (
        Long id,
        Instant placedTimestamp,
        String orderStatus
) {
}
