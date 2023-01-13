package com.pagingsorting.Controller;

import com.pagingsorting.Entity.Dto.ProductDto;
import com.pagingsorting.Entity.Product;
import com.pagingsorting.Service.Impl.ProductServiceImpl;
import com.pagingsorting.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/product")
    public String home(Model model , @RequestParam(name = "page" , required = false , defaultValue = "1") int page , @RequestParam(name = "sort" , required = false , defaultValue = "id") String field , @RequestParam(name = "keyword" , required = false , defaultValue = "") String keyword){
        Page<Product> pa = productService.listAll(page-1 , field, keyword);
        List<Product> products = pa.getContent();
        products.stream().map(product -> ProductServiceImpl.toDto(product));
        int totalPages = pa.getTotalPages();
        System.out.println(totalPages);
        int totalElements = (int) pa.getTotalElements();
        model.addAttribute("field" , field);
        model.addAttribute("page" , page);
        model.addAttribute("totalPages" , totalPages);
        model.addAttribute("totalElements" , totalElements);
        model.addAttribute("products" , products);
        model.addAttribute("keyword1" , keyword);
        return "List";
    }
    @GetMapping("/edit")
    public String edit(Model model , @RequestParam(name = "id" , required = false) int id){
        ProductDto productDto = productService.getById(id);
        model.addAttribute("edit" ,"Form Edit");
        model.addAttribute("productEdit", productDto);
        return "Edit";
    }

    @PostMapping("/edit")
    public String edit2(Model model , @RequestParam(name = "id" , required = false) int id ,@ModelAttribute("productDto") ProductDto productDto  , @RequestParam(name = "page" , required = false , defaultValue = "1") int page,@RequestParam(name = "sort" , required = false , defaultValue = "id") String field , @RequestParam(name = "keyword" , required = false , defaultValue = "") String keyword){
        productService.update(productDto,id);
        return home(model, page, field, keyword);

    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id" , required = true) int id , Model model , @RequestParam(name = "page" , required = false , defaultValue = "1") int page , @RequestParam(name = "sort" , required = false , defaultValue = "id") String field , @RequestParam(name = "keyword" , required = false , defaultValue = "") String keyword ){
        productService.delete(id);
        return home(model, page, field, keyword);
    }

    @GetMapping("/product/create")
    public String create(Model model){
        ProductDto productDto = new ProductDto();
        model.addAttribute("product" , productDto);
        return "Create";
    }
    @PostMapping("/product/create")
    public String created(@ModelAttribute("product") ProductDto productDto ,Model model , @RequestParam(name = "page" , required = false , defaultValue = "1") int page , @RequestParam(name = "sort" , required = false , defaultValue = "id") String field , @RequestParam(name = "keyword" , required = false , defaultValue = "") String keyword ){
        productService.save(productDto);
        model.addAttribute("message" , "Da Them San Pham Thanh Cong");
        return home(model, page, field, keyword);
    }
}
