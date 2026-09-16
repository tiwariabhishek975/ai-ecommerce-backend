package com.example.aiecommerce.service;
import com.example.aiecommerce.dto.ProductRequest;
import com.example.aiecommerce.entity.Product;
import com.example.aiecommerce.exception.ResourceNotFoundException;
import com.example.aiecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo){this.repo=repo;}
    public List<Product> findAll(){return repo.findAll();}
    public Product find(Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found: "+id));}
    public Product create(ProductRequest r){Product p=new Product();copy(p,r);return repo.save(p);}
    public Product update(Long id,ProductRequest r){Product p=find(id);copy(p,r);return repo.save(p);}
    public void delete(Long id){repo.delete(find(id));}
    private void copy(Product p,ProductRequest r){p.setName(r.name());p.setDescription(r.description());p.setPrice(r.price());p.setCategory(r.category());p.setStock(r.stock());p.setImage(r.image());}
}
