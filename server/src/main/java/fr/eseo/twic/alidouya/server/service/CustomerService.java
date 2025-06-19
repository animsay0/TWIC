package fr.eseo.twic.alidouya.server.service;

import fr.eseo.twic.alidouya.server.dto.CustomerDTO;
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

    /*public Page<Customer> searchCustomers(String query, Pageable pageable) {
        if (query == null || query.isBlank()) {
            return customerRepository.findAll(pageable);
        }
        return customerRepository.searchCustomers(query, pageable);
    }*/

    public Page<CustomerDTO> searchCustomers(String search, Pageable pageable) {
        if (search == null || search.isBlank()) {
            return customerRepository.findAll(pageable).map(this::toDto);
        }
        return customerRepository.searchCustomers(search, pageable).map(this::toDto);
    }

    private CustomerDTO toDto(Customer c) {
        return new CustomerDTO(
                c.getId(),
                c.getAccountNo(),
                c.getFirstName(),
                c.getLastName(),
                c.getEmail(),
                c.getRegistrationTimestamp()
        );
    }

    public CustomerDTO getCustomerByAccountNo(String accountNo) {
        return toDto(customerRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new RuntimeException("Customer not found")));
    }



}
