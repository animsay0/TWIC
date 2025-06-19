package fr.eseo.twic.alidouya.server.controller;


import fr.eseo.twic.alidouya.server.config.ApiRoutes;
import fr.eseo.twic.alidouya.server.model.Customer;
import fr.eseo.twic.alidouya.server.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiRoutes.BASE + "/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Page<Customer> getCustomers(
            @RequestParam(required = false) String search,
            Pageable pageable
    ) {
        return customerService.searchCustomers(search, pageable);
    }
}
