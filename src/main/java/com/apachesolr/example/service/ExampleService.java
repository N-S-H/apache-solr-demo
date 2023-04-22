package com.apachesolr.example.service;

import com.apachesolr.example.models.Product;
import com.apachesolr.example.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleService {


    @Autowired
    ExampleRepository productRepository;

    public Product saveProduct(Product productBean) {
        return productRepository.saveProduct(productBean);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductBySearchTerm(String searchTerm) {
        return productRepository.findBySearchTerm(searchTerm);
    }
}
