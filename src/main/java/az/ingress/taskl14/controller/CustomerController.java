package az.ingress.taskl14.controller;

import az.ingress.taskl14.model.request.CreateCustomerRequest;
import az.ingress.taskl14.model.request.UpdateSalaryRequest;
import az.ingress.taskl14.model.response.CustomerNameResponse;
import az.ingress.taskl14.model.response.CustomerResponse;
import az.ingress.taskl14.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CreateCustomerRequest request) {
        customerService.createCustomer(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @PatchMapping("/{id}/salary")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomerSalary(@PathVariable Long id,
                                     @RequestBody UpdateSalaryRequest request) {
        customerService.updateCustomerSalary(id, request);
    }

    @GetMapping("?name")
    public CustomerResponse getCustomerByName(String name) {
        return customerService.getCustomerByName(name);
    }

    @GetMapping("?birthDate")
    public CustomerNameResponse getCustomerNameByBirthDate(@RequestParam LocalDate birthDate){
        return customerService.getCustomerNameByBirthDate(birthDate);
    }
}

