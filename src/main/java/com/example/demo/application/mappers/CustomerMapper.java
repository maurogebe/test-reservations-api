package com.example.demo.application.mappers;

import com.example.demo.application.dtos.CustomerDTO;
import com.example.demo.domain.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
    
    List<CustomerDTO> customersToCustomersDTO(List<Customer> customers);
    
    List<Customer> customersDTOToCustomers(List<CustomerDTO> customersDTO);

}
