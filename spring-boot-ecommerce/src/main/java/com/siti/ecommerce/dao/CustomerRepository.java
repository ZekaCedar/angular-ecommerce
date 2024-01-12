package com.siti.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siti.ecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
