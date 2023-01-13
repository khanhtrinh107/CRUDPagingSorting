package com.pagingsorting.Service.Impl;

import com.pagingsorting.Entity.Dto.ProductDto;
import com.pagingsorting.Entity.Product;
import com.pagingsorting.Repository.ProductRepository;
import com.pagingsorting.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Page<Product> listAll(int page , String field , String keyword) {
        Sort sort = Sort.by(field);
        Pageable pageable = PageRequest.of(page,4,sort);
        Page<Product> pa= productRepository.find(keyword,pageable);
        return pa;
    }

    @Override
    public ProductDto getById(Integer id) {
        return toDto(productRepository.findById(id).orElseThrow());
    }

    @Override
    public void save(ProductDto productDto) {
        Product product = new Product();
        product.setBrand(productDto.getBrand());
        product.setName(productDto.getName());
        product.setMadeIn(productDto.getMadeIn());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

    @Override
    public void update(ProductDto productDto, Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setBrand(productDto.getBrand());
        product.setName(productDto.getName());
        product.setMadeIn(productDto.getMadeIn());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public static ProductDto toDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setBrand(product.getBrand());
        dto.setMadeIn(product.getMadeIn());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
