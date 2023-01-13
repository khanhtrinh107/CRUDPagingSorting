package com.pagingsorting.Service;

import com.pagingsorting.Entity.Dto.ProductDto;
import com.pagingsorting.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<Product> listAll(int page, String field, String keyword);
    ProductDto getById(Integer id);
    void save(ProductDto productDto);

    void update(ProductDto productDto , Integer id);
    void delete(Integer id);
}
