package fr.eseo.twic.alidouya.server.dto;

public record CustomerDTO<Instant>(Long id,
                                   String accountNo,
                                   String firstName,
                                   String lastName,
                                   String email,
                                   Instant registrationTimestamp) {
}
