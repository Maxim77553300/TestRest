package com.example.TestRest.repository;

import com.example.TestRest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Repository interface for {@link }.
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
