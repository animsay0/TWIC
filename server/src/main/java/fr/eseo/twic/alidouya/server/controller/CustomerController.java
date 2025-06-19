package fr.eseo.twic.alidouya.server.controller;


import fr.eseo.twic.alidouya.server.config.ApiRoutes;
import fr.eseo.twic.alidouya.server.dto.CustomerDTO;
import fr.eseo.twic.alidouya.server.model.Customer;
import fr.eseo.twic.alidouya.server.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiRoutes.BASE + "/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Page<CustomerDTO> getCustomers(
            @RequestParam(required = false) String search,
            Pageable pageable
    ) {
        return customerService.searchCustomers(search, pageable);
    }

    @GetMapping("/{accountNo}")
    public CustomerDTO getCustomerByAccountNo(@PathVariable String accountNo) {
        return customerService.getCustomerByAccountNo(accountNo);
    }

}
