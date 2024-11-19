package com.gv.yummyrest.service;

import com.gv.yummyrest.dto.CustomerRequest;
import com.gv.yummyrest.entity.Customer;
import com.gv.yummyrest.mapper.CustomerMapper;
import com.gv.yummyrest.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
}
