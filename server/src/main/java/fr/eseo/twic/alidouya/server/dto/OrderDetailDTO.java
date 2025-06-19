package fr.eseo.twic.alidouya.server.dto;

public record OrderDetailDTO(Long id,
                             String productNo,
                             String productName,
                             Integer quantity) {
}
