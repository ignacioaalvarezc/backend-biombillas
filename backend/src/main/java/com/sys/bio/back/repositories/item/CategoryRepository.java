package com.sys.bio.back.repositories.item;

import com.sys.bio.back.models.item.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
