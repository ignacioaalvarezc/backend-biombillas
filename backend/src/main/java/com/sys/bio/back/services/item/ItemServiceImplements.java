package com.sys.bio.back.services.item;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.enums.StoreMovement;
import com.sys.bio.back.models.item.Item;
import com.sys.bio.back.models.item.Store;
import com.sys.bio.back.repositories.item.ItemRepository;
import com.sys.bio.back.repositories.item.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ItemServiceImplements implements ItemService {

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private StoreRepository storeRepo;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    public Item addItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public Set<Item> getItems() {
        return new LinkedHashSet<>(itemRepo.findAll());
    }

    @Override
    public Item getItem(Long itemId) {
        return itemRepo.findById(itemId).get();
    }

    @Override
    public void deleteItem(Long itemId) {
        Item item = new Item();
        item.setItemId(itemId);
        itemRepo.delete(item);
    }

    @Override
    public Item updateCurrentAmount(Long itemId, int amount) {
        Item item = getItem(itemId);
        if (item != null) {
            item.setCurrentAmount(amount);
            return itemRepo.save(item);
        }
        return null;
    }

    @Override
    public Store addMovement(Long itemId, int amount, StoreMovement movement) {
        Item item = getItem(itemId);
        if (item != null) {
            Store store = new Store();
            store.setItem(item);
            store.setAmount(amount);
            store.setMovement(movement);
            store.setDate(LocalDate.now());

            if (movement == StoreMovement.INGRESO) {
                item.setCurrentAmount(item.getCurrentAmount() + amount);
            } else if (movement == StoreMovement.EGRESO) {
                item.setCurrentAmount(item.getCurrentAmount() - amount);
            }

            itemRepo.save(item);
            return storeRepo.save(store);
        }
        return null;
    }

    @Override
    public List<Store> getMovementsByItemAndDate(Long itemId, LocalDate startDate, LocalDate endDate) {
        Item item = getItem(itemId);
        if (item != null) {
            return storeRepo.findByItemAndDateBetween(item, startDate, endDate);
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return (List<Item>) itemRepo.findAll();
    }

}
