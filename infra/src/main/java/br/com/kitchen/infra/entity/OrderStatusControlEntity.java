package br.com.kitchen.infra.entity;

import br.com.kitchen.domain.core.domain.entities.OrderStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "orderStatusControl")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusControlEntity {

    @Id
    private String orderId;

    private OrderStatus orderStatus;

    private LocalDate lastUpdate;
}
