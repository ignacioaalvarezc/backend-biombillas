package com.sys.bio.back.controllers.item;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.enums.StoreMovement;
import com.sys.bio.back.models.item.Inventory;
import com.sys.bio.back.models.item.Item;
import com.sys.bio.back.models.item.Store;
import com.sys.bio.back.services.item.ItemService;
import com.sys.bio.back.services.item.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory/movements")
public class InventoryMovementController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add/{productId}")
    public Inventory addMovement(@PathVariable Long productId, @RequestBody Map<String, Object> payload) {
        int amount = (Integer) payload.get("amount");
        StoreMovement storeMovement = StoreMovement.valueOf((String) payload.get("storeMovement"));
        return productService.addMovement(productId, amount, storeMovement);
    }

    @GetMapping("/list/{productId}")
    public List<Inventory> getMovementsByProductAndDate(@PathVariable Long productId, @RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return productService.getMovementsByProductAndDate(productId, start, end);
    }
}
