package com.sys.bio.back.services.item;

import com.sys.bio.back.models.item.Category;
import com.sys.bio.back.models.item.Inventory;

import java.util.List;
import java.util.Set;

public interface InventoryService {
    Inventory addInventory(Inventory inventory);

    Inventory updateInventory(Inventory inventory);

    Set<Inventory> getInventories();

    Inventory getInventory(Long inventoryId);

    void deleteInventory(Long inventoryId);

}
