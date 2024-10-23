package com.sys.bio.back.controllers.item;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.item.Item;
import com.sys.bio.back.models.item.Product;
import com.sys.bio.back.services.item.ItemService;
import com.sys.bio.back.services.item.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId,
                                           @Valid @RequestBody Product product) {
        try {
            product.setProductId(productId);
            Product updatedProduct = productService.updateProduct(product);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            log.error("Error al actualizar el producto con ID: " + productId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/id/{productId}")
    public Product listProduct(@PathVariable("productId") Long productId) {
        return productService.getProduct(productId);
    }


    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping("/update-amount")
    public ResponseEntity<Void> updateCurrentAmount(
            @RequestParam Long providerId,
            @RequestParam Long boxNameId,
            @RequestParam Long boxFormatId,
            @RequestParam int amount) {
        productService.updateCurrentAmount(providerId, boxNameId, boxFormatId, amount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id")
    public Long getProductId(@RequestParam Long providerId, @RequestParam Long boxNameId, @RequestParam Long boxFormatId) {
        return productService.getProductId(providerId, boxNameId, boxFormatId);
    }
}
