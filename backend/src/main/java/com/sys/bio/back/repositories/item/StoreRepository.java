package com.sys.bio.back.repositories.item;

import com.sys.bio.back.models.item.Item;
import com.sys.bio.back.models.item.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByItemAndDateBetween(Item item, LocalDate startDate, LocalDate endDate);
}
