package com.gv.yummyrest.service;

import com.gv.yummyrest.dto.CustomerRequest;
import com.gv.yummyrest.entity.Customer;
import com.gv.yummyrest.mapper.CustomerMapper;
import com.gv.yummyrest.repo.CustomerRepo;
import com.gv.yummyrest.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper mapper;
    private final JwtUtil jwtUtil;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }

    public String login(String email, String rawPassword) {
        Customer customer = repo.findByEmail(email);
        if (customer != null && passwordEncoder.matches(rawPassword, customer.getPassword())) {
            return jwtUtil.generateToken(email);
        }
        throw new RuntimeException("Invalid email or password");
    }

    public void deleteCustomer(Long id) {
        repo.deleteById(id);
    }

    public Customer updateCustomer(Long id, CustomerRequest request) {
        Customer customer = repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        // Do not update email and password
        return repo.save(customer);
    }
}