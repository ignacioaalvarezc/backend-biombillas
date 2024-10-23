package com.sys.bio.back.controllers.item;

import com.sys.bio.back.models.enums.StoreMovement;
import com.sys.bio.back.models.item.Store;
import com.sys.bio.back.services.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movements")
public class StoreMovementController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/add/{itemId}")
    public Store addMovement(@PathVariable Long itemId, @RequestBody Map<String, Object> payload) {
        int amount = (Integer) payload.get("amount");
        StoreMovement storeMovement = StoreMovement.valueOf((String) payload.get("storeMovement"));
        return itemService.addMovement(itemId, amount, storeMovement);
    }

    @GetMapping("/list/{itemId}")
    public List<Store> getMovementsByItemAndDate(@PathVariable Long itemId, @RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return itemService.getMovementsByItemAndDate(itemId, start, end);
    }
}
