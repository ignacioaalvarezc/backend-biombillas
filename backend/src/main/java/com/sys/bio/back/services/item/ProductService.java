package com.sys.bio.back.services.item;

import com.sys.bio.back.models.enums.StoreMovement;
import com.sys.bio.back.models.item.Inventory;
import com.sys.bio.back.models.item.Item;
import com.sys.bio.back.models.item.Product;
import com.sys.bio.back.models.item.Store;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ProductService {

    Product addProduct(Product product);

    Product updateProduct(Product product);

    Set<Product> getProducts();

    Product updateCurrentAmount(Long productId, int amount);

    Inventory addMovement(Long productId, int amount, StoreMovement movement);

    List<Inventory> getMovementsByProductAndDate(Long productId, LocalDate startDate, LocalDate endDate);

    public List<Product> findAll();

    Product getProduct(Long productId);

    void deleteProduct(Long productId);

    @Transactional
    void updateCurrentAmount(Long providerId, Long boxNameId, Long boxFormatId, int amount);

    Long getProductId(Long providerId, Long boxNameId, Long boxFormatId);
}
