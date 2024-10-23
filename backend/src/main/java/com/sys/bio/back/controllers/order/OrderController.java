package com.sys.bio.back.controllers.order;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.order.Order;
import com.sys.bio.back.models.order.OrderProduct;
import com.sys.bio.back.services.order.OrderService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") Long orderId,
                                                     @Valid @RequestBody Order order) {
        try {
            order.setOrderId(orderId);
            Order updatedOrder = orderService.updateOrder(order);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            log.error("Error al actualizar la orden con ID: " + orderId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("/id/{orderId}")
    public Order listOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getOrder(orderId);
    }


    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
    }


    @GetMapping("/date-filter")
    public ResponseEntity<List<Order>> getFilteredOrders(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Order> filteredOrders = orderService.getOrdersByPickUpDateRange(startDate, endDate);
        return new ResponseEntity<>(filteredOrders, HttpStatus.OK);
    }

    @GetMapping("/count")
    public long getUnprocessedOrdersCount() {
        return orderService.getUnprocessedOrdersCount();
    }




}
