package com.apachesolr.example.controller;

import com.apachesolr.example.models.Product;
import com.apachesolr.example.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solr")
public class ExampleController {

    @Autowired
    ExampleService service;

    @PostMapping("")
    public Product saveProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") String id) {
        return service.getProductById(id);
    }

    @GetMapping("/search/{term}")
    public List<Product> getProductBySearchTerm(@PathVariable("term") String searchTerm) {
        return service.getProductBySearchTerm(searchTerm);
    }
}
