package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable("id") Integer id){
        return productService.findById(id);
    }

    @GetMapping()
    public List<ProductDTO> findAll(){
        return productService.findAll();
    }

    @PostMapping()
    public Integer insert(@RequestBody ProductDTO productDTO) {
        return productService.insert(productDTO);
    }

    @PutMapping()
    public Integer update(@RequestBody ProductDTO productDTO) {
        return productService.update(productDTO);
    }

    @DeleteMapping(value = "/delete")
    public Integer delete(@RequestBody ProductDTO productDTO) {
        System.out.println(productDTO.toString());
        return productService.delete(productDTO);
    }
}
