package se.iths.java23.petterlundqvist.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java23.petterlundqvist.webshop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
