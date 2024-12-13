package az.ingress.taskl14.service;

import az.ingress.taskl14.dao.entity.CustomerEntity;
import az.ingress.taskl14.dao.repository.CustomerRepository;
import az.ingress.taskl14.model.enums.CustomerStatus;
import az.ingress.taskl14.model.request.CreateCustomerRequest;
import az.ingress.taskl14.model.request.UpdateSalaryRequest;
import az.ingress.taskl14.model.response.CustomerNameResponse;
import az.ingress.taskl14.model.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomerServiceHandler implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public void createCustomer(CreateCustomerRequest request) {
        customerRepository.save(CustomerEntity.builder()
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .country(request.getCountry())
                .salary(request.getSalary())
                .status(CustomerStatus.ACTIVE)
                .build());
    }

    @Override
    public void deleteCustomer(Long id) {
        var customer = fetchCustomerIfExits(id);
        customer.setStatus(CustomerStatus.DELETED);
        customerRepository.save(customer);
    }

    @Override
    public CustomerResponse getCustomer(Long id) {
        var customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getBirthDate(),
                customer.getCountry(),
                customer.getSalary()
        );
    }

    @Override
    public void updateCustomerSalary(Long id, UpdateSalaryRequest request) {
        var customer = fetchCustomerIfExits(id);
        customer.setSalary(request.getSalary());
        customerRepository.save(customer);
    }

    @Override
    public CustomerResponse getCustomerByName(String name) {
        var customer = customerRepository.findByName(name).orElseThrow(RuntimeException::new);
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getBirthDate(),
                customer.getCountry(),
                customer.getSalary());
    }

    @Override
    public CustomerNameResponse getCustomerNameByBirthDate(LocalDate birthDate){
        var name = customerRepository.findNameByBirthDate(birthDate).orElseThrow(RuntimeException::new);
        return new CustomerNameResponse(name.getName());
    }

    private CustomerEntity fetchCustomerIfExits(Long id) {
        return customerRepository.findByIdAndStatus(id, CustomerStatus.ACTIVE)
                .orElseThrow(RuntimeException::new);
    }
}
