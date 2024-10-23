package com.sys.bio.back.services.item;

import com.sys.bio.back.models.item.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    Category addCategory(Category category);

    Category updateCategory(Category category);

    Set<Category> getCategories();

    public List<Category> findAll();

    Category getCategory(Long categoryId);

    void deleteCategory(Long categoryId);
}
