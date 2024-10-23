package com.sys.bio.back.services.item;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.enums.StoreMovement;
import com.sys.bio.back.models.item.Inventory;
import com.sys.bio.back.models.item.Product;
import com.sys.bio.back.repositories.item.InventoryRepository;
import com.sys.bio.back.repositories.item.ProductRepository;
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
public class ProductServiceImplements implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private InventoryRepository inventoryRepo;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Set<Product> getProducts() {
        return new LinkedHashSet<>(productRepo.findAll());
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepo.findById(productId).get();
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = new Product();
        product.setProductId(productId);
        productRepo.delete(product);
    }

    @Override
    public Product updateCurrentAmount(Long productId, int amount) {
        Product product = getProduct(productId);
        if (product != null) {
            product.setCurrentAmount(amount);
            return productRepo.save(product);
        }
        return null;
    }

    @Override
    public Inventory addMovement(Long productId, int amount, StoreMovement movement) {
        Product product = getProduct(productId);
        if (product != null) {
            Inventory inventory = new Inventory();
            inventory.setProduct(product);
            inventory.setAmount(amount);
            inventory.setMovement(movement);
            inventory.setDate(LocalDate.now());

            if (movement == StoreMovement.INGRESO) {
                product.setCurrentAmount(product.getCurrentAmount() + amount);
            } else if (movement == StoreMovement.EGRESO) {
                product.setCurrentAmount(product.getCurrentAmount() - amount);
            }

            productRepo.save(product);
            return inventoryRepo.save(inventory);
        }
        return null;
    }

    @Override
    public List<Inventory> getMovementsByProductAndDate(Long productId, LocalDate startDate, LocalDate endDate) {
        Product product = getProduct(productId);
        if (product != null) {
            return inventoryRepo.findByProductAndDateBetween(product, startDate, endDate);
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productRepo.findAll();
    }

    @Override
    @Transactional
    public void updateCurrentAmount(Long providerId, Long boxNameId, Long boxFormatId, int amount) {
        Product product = productRepo.findByProviderProviderIdAndBoxNameBoxNameIdAndBoxFormatBoxFormatId(providerId, boxNameId, boxFormatId);
        if (product != null) {
            product.setCurrentAmount(product.getCurrentAmount() + amount);
            productRepo.save(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public Long getProductId(Long providerId, Long boxNameId, Long boxFormatId) {
        return productRepo.findByProviderProviderIdAndBoxNameBoxNameIdAndBoxFormatBoxFormatId(providerId, boxNameId, boxFormatId).getProductId();
    }
}
