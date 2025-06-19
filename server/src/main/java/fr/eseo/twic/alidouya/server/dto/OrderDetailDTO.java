package fr.eseo.twic.alidouya.server.dto;

public record OrderDetailDTO(Long id,
                             String productCode,
                             String productName,
                             Integer quantity) {
}
