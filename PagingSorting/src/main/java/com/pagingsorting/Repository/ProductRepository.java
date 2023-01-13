package com.pagingsorting.Repository;

import com.pagingsorting.Entity.Dto.ProductDto;
import com.pagingsorting.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> , JpaRepository<Product,Integer> {
    @Query("SELECT p from Product p where p.brand like %?1% or p.madeIn like %?1% or p.name like %?1%")
    Page<Product> find(String keyword, Pageable pageable);
}
