package com.siti.ecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.siti.ecommerce.entity.Product;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
	
	/* @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%')") */
	Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
}
