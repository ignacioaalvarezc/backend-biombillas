package com.sys.bio.back.controllers.order;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.order.Order;
import com.sys.bio.back.models.order.OrderProduct;
import com.sys.bio.back.models.packaging.Package;
import com.sys.bio.back.services.order.OrderProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderProducts")
@CrossOrigin("*")
public class OrderProductController {

    @Autowired
    private OrderProductService orderProductService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<OrderProduct> saveOrderProduct(@RequestBody OrderProduct orderProduct) {
        OrderProduct savedOrderProduct = orderProductService.addOrderProduct(orderProduct);
        return ResponseEntity.ok(savedOrderProduct);
    }

    @GetMapping("/id/{orderProductId}")
    @RequestMapping(method = RequestMethod.GET, path = "/id/{orderProductId}")
    public OrderProduct listOrderProductById(@PathVariable("orderProductId") Long orderProductId) {
        return orderProductService.getOrderProduct(orderProductId);
    }

    @GetMapping("/")
    public ResponseEntity<?> orderProductList() {
        return ResponseEntity.ok(orderProductService.getOrderProducts());
    }

    @PutMapping("/update/{orderProductId}")
    public ResponseEntity<OrderProduct> updateOrderProduct(@PathVariable("orderProductId") Long orderProductId,
                                                 @Valid @RequestBody OrderProduct orderProduct) {
        try {
            orderProduct.setOrderProductId(orderProductId);
            OrderProduct updatedOrderProduct = orderProductService.updateOrderProduct(orderProduct);
            return ResponseEntity.ok(updatedOrderProduct);
        } catch (Exception e){
            log.error("Error al actualizar el producto de orden con ID: " + orderProductId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{orderProductId}")
    public void deleteOrderProduct(@PathVariable("orderProductId") Long orderProductId) {
        orderProductService.deleteOrderProduct(orderProductId);
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderProduct>> list() {
        List<OrderProduct> list = orderProductService.findAll();
        return new ResponseEntity<List<OrderProduct>>(list, HttpStatus.OK);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAllOrderProducts(@RequestBody List<OrderProduct> orderProducts) {
        try {
            orderProductService.saveAll(orderProducts);
            return ResponseEntity.ok("Order Products has saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving order products: " + e.getMessage());
        }
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format: " + ex.getMessage());
    }

    @GetMapping("/byOrder/{orderId}")
    public ResponseEntity<List<OrderProduct>> getOrderProductsByOrderId(@PathVariable Long orderId) {
        List<OrderProduct> orderProducts = orderProductService.getOrderProductsByOrderId(orderId);
        return new ResponseEntity<>(orderProducts, HttpStatus.OK);
    }
}
