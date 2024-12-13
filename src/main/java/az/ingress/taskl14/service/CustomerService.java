package az.ingress.taskl14.service;

import az.ingress.taskl14.model.request.CreateCustomerRequest;
import az.ingress.taskl14.model.request.UpdateSalaryRequest;
import az.ingress.taskl14.model.response.CustomerNameResponse;
import az.ingress.taskl14.model.response.CustomerResponse;

import java.time.LocalDate;

public interface CustomerService {

    void createCustomer(CreateCustomerRequest request);

    public void deleteCustomer(Long id);

    CustomerResponse getCustomer(Long id);

    void updateCustomerSalary(Long id, UpdateSalaryRequest request);

    CustomerResponse getCustomerByName(String name);

    CustomerNameResponse getCustomerNameByBirthDate(LocalDate birthDate);
}
