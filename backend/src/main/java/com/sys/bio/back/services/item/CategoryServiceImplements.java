package com.sys.bio.back.services.item;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.item.Category;
import com.sys.bio.back.repositories.item.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImplements implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(categoryRepo.findAll());
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepo.findById(categoryId).get();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        categoryRepo.delete(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return (List<Category>) categoryRepo.findAll();
    }

}
