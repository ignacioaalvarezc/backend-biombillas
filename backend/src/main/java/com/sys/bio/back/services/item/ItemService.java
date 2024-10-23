package com.sys.bio.back.services.item;


import com.sys.bio.back.models.enums.StoreMovement;
import com.sys.bio.back.models.item.Item;
import com.sys.bio.back.models.item.Store;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ItemService {

    Item addItem(Item item);

    Item updateItem(Item item);

    Set<Item> getItems();

    Item updateCurrentAmount(Long itemId, int amount);

    Store addMovement(Long itemId, int amount, StoreMovement movement);

    List<Store> getMovementsByItemAndDate(Long itemId, LocalDate startDate, LocalDate endDate);

    public List<Item> findAll();

    Item getItem(Long itemId);

    void deleteItem(Long itemId);

}
