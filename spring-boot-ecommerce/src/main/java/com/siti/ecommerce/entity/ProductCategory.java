package com.siti.ecommerce.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="product_category")
@Data
public class ProductCategory {
	
	/**
     * Attribute ID: A001
     * Key: id
     * Description: Unique identifier for the category. Required field.
     * Constraints: Not null, Auto-generated
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	/**
     * Attribute ID: A002
     * Key: categoryName
     * Description: Descriptive name of the category. Required field.
     * Constraints: Not null, Max length 255
     */
	@Column(name = "category_name", nullable = false, length = 255)
	private String categoryName;
	
	/**
     * Attribute ID: A003
     * Key: products
     * Description: Set of products associated with this category. Represents the one-to-many relationship.
     * Constraints: Can be empty, Cascade type ALL
     */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private Set<Product> products;
}
