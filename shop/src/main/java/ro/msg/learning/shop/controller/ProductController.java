package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.dto.converter.ProductConverter;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(ProductConverter.generateDTOFromEntity(productService.findById(id)));
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.ok(productService.findAll().stream().map(ProductConverter::generateDTOFromEntity).collect(Collectors.toList()));
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(ProductConverter.generateDTOFromEntity(productService.insert(ProductConverter.generateEntityFromDTO(productDTO))));
    }

    @PutMapping()
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(ProductConverter.generateDTOFromEntity(productService.update(ProductConverter.generateEntityFromDTO(productDTO))));
    }

    @DeleteMapping()
    public ResponseEntity<Integer> delete(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.delete(ProductConverter.generateEntityFromDTO(productDTO)));
    }
}
