package com.gv.yummyrest.mapper;

import com.gv.yummyrest.dto.CustomerRequest;
import com.gv.yummyrest.entity.Customer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    private final PasswordEncoder passwordEncoder;

    public CustomerMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

    }

    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
    }
}