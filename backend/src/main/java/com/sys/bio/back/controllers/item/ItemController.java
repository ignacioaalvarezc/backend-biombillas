package com.sys.bio.back.controllers.item;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.item.Item;
import com.sys.bio.back.services.item.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/items")
@CrossOrigin("*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/")
    public ResponseEntity<Item> saveItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.addItem(item));
    }

    @PutMapping("/update/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable("itemId") Long itemId,
                                                   @Valid @RequestBody Item item) {
        try {
            item.setItemId(itemId);
            Item updatedItem = itemService.updateItem(item);
            return ResponseEntity.ok(updatedItem);
        } catch (Exception e) {
            log.error("Error al actualizar el item con ID: " + itemId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listItems() {
        return ResponseEntity.ok(itemService.getItems());
    }

    @GetMapping("/id/{itemId}")
    public Item listItem(@PathVariable("itemId") Long itemId) {
        return itemService.getItem(itemId);
    }


    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }

    @PutMapping("/{itemId}/amount")
    public Item updateCurrentAmount(@PathVariable Long itemId, @RequestBody Map<String, Integer> payload) {
        return itemService.updateCurrentAmount(itemId, payload.get("amount"));
    }
}
