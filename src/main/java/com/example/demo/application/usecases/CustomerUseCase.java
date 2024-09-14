package com.example.demo.application.usecases;

import com.example.demo.application.dtos.CustomerDTO;
import com.example.demo.application.exeptions.ApiRequestException;
import com.example.demo.application.mappers.CustomerMapper;
import com.example.demo.domain.entities.Country;
import com.example.demo.domain.entities.Customer;
import com.example.demo.domain.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerUseCase {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customerById = this.customerRepository.findById(id);

        if(customerById.isEmpty()) throw new ApiRequestException("No se encontró el cliente con ID: " + id, HttpStatus.NOT_FOUND);

        return CustomerMapper.INSTANCE.customerToCustomerDTO(customerById.get());
    }
    
    public List<CustomerDTO> getCustomers() {
        List<Customer> customers = this.customerRepository.findAll();
        return CustomerMapper.INSTANCE.customersToCustomersDTO(customers);
    }

    public void createCustomer(CustomerDTO customer) {
        Optional<Customer> customerByEmail = this.customerRepository.findByEmail(customer.getEmail());
        if(customerByEmail.isPresent()) throw new ApiRequestException("Ya existe un cliente con este correo: " + customer.getEmail(), HttpStatus.BAD_REQUEST);
        this.customerRepository.save(CustomerMapper.INSTANCE.customerDTOToCustomer(customer));
    }
}
