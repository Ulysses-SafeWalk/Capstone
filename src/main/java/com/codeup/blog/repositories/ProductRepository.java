package com.codeup.blog.repositories;

import com.codeup.blog.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
