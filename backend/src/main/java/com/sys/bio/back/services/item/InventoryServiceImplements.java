package com.sys.bio.back.services.item;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.item.Category;
import com.sys.bio.back.models.item.Inventory;
import com.sys.bio.back.repositories.item.CategoryRepository;
import com.sys.bio.back.repositories.item.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class InventoryServiceImplements implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepo;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    public Inventory addInventory(Inventory inventory) {
        return inventoryRepo.save(inventory);
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepo.save(inventory);
    }

    @Override
    public Set<Inventory> getInventories() {
        return new LinkedHashSet<>(inventoryRepo.findAll());
    }

    @Override
    public Inventory getInventory(Long inventoryId) {
        return inventoryRepo.findById(inventoryId).get();
    }

    @Override
    public void deleteInventory(Long inventoryId) {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(inventoryId);
        inventoryRepo.delete(inventory);
    }
}
