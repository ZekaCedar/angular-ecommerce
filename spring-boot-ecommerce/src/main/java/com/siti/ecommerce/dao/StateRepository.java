package com.siti.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.siti.ecommerce.entity.State;

@CrossOrigin("http://localhost:4200")
public interface StateRepository extends JpaRepository<State, Integer>{
	
	List<State> findByCountryCode(@Param("code") String code);
	
}
