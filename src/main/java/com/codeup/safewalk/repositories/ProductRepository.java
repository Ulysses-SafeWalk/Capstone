package com.codeup.safewalk.repositories;

import com.codeup.safewalk.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
