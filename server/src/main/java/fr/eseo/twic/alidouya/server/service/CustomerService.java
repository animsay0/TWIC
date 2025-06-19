package fr.eseo.twic.alidouya.server.service;

import fr.eseo.twic.alidouya.server.model.Customer;
import fr.eseo.twic.alidouya.server.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<Customer> searchCustomers(String query, Pageable pageable) {
        if (query == null || query.isBlank()) {
            return customerRepository.findAll(pageable);
        }
        return customerRepository.searchCustomers(query, pageable);
    }


}
