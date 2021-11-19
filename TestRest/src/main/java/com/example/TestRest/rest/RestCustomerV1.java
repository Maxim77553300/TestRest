package com.example.TestRest.rest;

import com.example.TestRest.model.Customer;
import com.example.TestRest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/customers/")
public class RestCustomerV1 {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "{id}",method = RequestMethod.GET , produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long customerId){
        if(customerId == null){
            return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
        }
        Customer customer = this.customerService.getById(customerId);
        if(customer == null){
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "",method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Validated Customer customer){
        HttpHeaders headers = new HttpHeaders();

        if(customer == null){
            return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
        }
        this.customerService.save(customer);

        return new ResponseEntity<>(customer, headers, HttpStatus.CREATED);

    }

    @RequestMapping(value = "",method = RequestMethod.PUT,produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(Customer customer, UriComponentsBuilder builder){
        HttpHeaders headers = new HttpHeaders();

        if(customer == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.customerService.save(customer);

        return new ResponseEntity<>(customer,headers,HttpStatus.OK);

        }

        @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<Customer> deleteCustomer(@PathVariable("id")  Long id){
        Customer customer = this.customerService.getById(id);

        if(customer == null){
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }

        this.customerService.delete(id);

        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
        }

        @RequestMapping(value = "", method = RequestMethod.GET,produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = this.customerService.getAll();

        if(customers.isEmpty()){
            return new ResponseEntity<>(customers, HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
}
