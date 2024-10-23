package com.sys.bio.back.repositories.item;

import com.sys.bio.back.models.item.Inventory;
import com.sys.bio.back.models.item.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByProductAndDateBetween(Product product, LocalDate startDate, LocalDate endDate);
}
