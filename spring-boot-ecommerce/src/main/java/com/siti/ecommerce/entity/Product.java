package com.siti.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="product")
@Data
public class Product {
	
	/**
     * Attribute ID: A001
     * Key: id
     * Description: Autogenerated unique identifier for each product. Required field.
     * Constraints: Not null, Auto-generated
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	/**
	 * Attribute ID: A011
	 * Key: category
	 * Description: The product category to which this product belongs. This field represents the many-to-one 
	 * relationship between the product and its category, indicating that each product is associated with one category.
	 * Constraints: Not null, Foreign key referencing the ProductCategory entity.
	 */
	@ManyToOne
	@JoinColumn(name="category_id", nullable = false)
	private ProductCategory category;
	
	/**
     * Attribute ID: A002
     * Key: sku
     * Description: Stock Keeping Unit, a unique identifier for each distinct product. Required field.
     * Constraints: Not null
     */
	@Column(name = "sku", nullable = false)
	private String sku;
	
	/**
     * Attribute ID: A003
     * Key: name
     * Description: The name of the product. Required field.
     * Constraints: Not null
     */
	@Column(name = "name", nullable = false)
	private String name;
	
	/**
     * Attribute ID: A004
     * Key: description
     * Description: A detailed description of the product.
     * Constraints: Can be null
     */
	@Column(name = "description")
	private String description;
	
	 /**
     * Attribute ID: A005
     * Key: unitPrice
     * Description: The price for a single unit of the product. Required field.
     * Constraints: Not null, Positive value
     */
	@Column(name = "unit_price", nullable = false)
	private BigDecimal unitPrice;
	
	/**
     * Attribute ID: A006
     * Key: imageUrl
     * Description: URL of the image representing the product.
     * Constraints: Can be null
     */
	@Column(name = "image_url")
	private String imageUrl;
	
	/**
     * Attribute ID: A007
     * Key: active
     * Description: Indicates whether the product is active and available for purchase. Required field.
     * Constraints: Not null
     */
	@Column(name = "active", nullable = false)
	private Boolean active;
	
	/**
     * Attribute ID: A008
     * Key: unitsInStock
     * Description: The number of units currently in stock. Required field.
     * Constraints: Not null, Non-negative
     */
	@Column(name = "units_in_stock", nullable = false)
	private int unitsInStock;
	
	/**
     * Attribute ID: A009
     * Key: dateCreated
     * Description: The timestamp when the product was first created in the system. Auto-populated.
     * Constraints: Auto-generated, Not null
     */
	@Column(name = "date_created", nullable = false)
	@CreationTimestamp
	private Date dateCreated;
	
	/**
     * Attribute ID: A010
     * Key: lastUpdated
     * Description: The timestamp of the last update to the product's information. Auto-populated.
     * Constraints: Auto-generated, Not null
     */
	@Column(name = "last_updated", nullable = false)
	@UpdateTimestamp
	private Date lastUpdated;

}
