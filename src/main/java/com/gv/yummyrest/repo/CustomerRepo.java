package com.gv.yummyrest.repo;

import com.gv.yummyrest.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
