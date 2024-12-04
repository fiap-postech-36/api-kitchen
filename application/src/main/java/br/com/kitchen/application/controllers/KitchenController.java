package br.com.kitchen.application.controllers;

import br.com.kitchen.application.facade.OrderStatusControlFacade;
import br.com.kitchen.application.inout.input.OrderStatusControlInput;
import br.com.kitchen.domain.core.domain.entities.OrderStatusControl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kitchen")
@AllArgsConstructor
public class KitchenController {

    private final OrderStatusControlFacade orderStatusControlFacade;

    @PutMapping
    public ResponseEntity<String> updateOrderStatusControl(@RequestBody @Valid OrderStatusControlInput request) {
        orderStatusControlFacade.updateOrder(request);
        return ResponseEntity.ok().body("OK");
    }

    @PutMapping("/cancel")
    public ResponseEntity<String> cancelOrderStatusControl(@RequestBody @Valid OrderStatusControlInput request) {
        orderStatusControlFacade.cancelOrder(request);
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping
    public ResponseEntity<List<OrderStatusControl>> getAllOrderStatusControl() {
        return ResponseEntity.ok().body(orderStatusControlFacade.getAllOrder());
    }
}
