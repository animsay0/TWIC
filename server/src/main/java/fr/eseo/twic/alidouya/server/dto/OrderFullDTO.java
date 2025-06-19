package fr.eseo.twic.alidouya.server.dto;

import java.time.Instant;
import java.util.List;

public record OrderFullDTO(Long id,
                           String accountNo,
                           String orderStatus,
                           Instant placedTimestamp,
                           Instant deliveredTimestamp,
                           Instant cancelledTimestamp,
                           Double orderTotal,
                           List<OrderDetailDTO> orderDetails) {
}
