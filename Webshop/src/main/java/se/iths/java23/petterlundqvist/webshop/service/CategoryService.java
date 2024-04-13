package se.iths.java23.petterlundqvist.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.java23.petterlundqvist.webshop.model.Category;
import se.iths.java23.petterlundqvist.webshop.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategory(String name) {
        return categoryRepository.findByName(name);
    }
}
