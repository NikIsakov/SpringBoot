package ru.geekbrains.boot.services;


import ru.geekbrains.boot.exceptions.ResourceNotFoundException;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.model.Student;
import ru.geekbrains.boot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

    public void save(Product product) {
        productRepository.add(product);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public int totalProduct(){
        List<Product> products = productRepository.getProducts();
        return products.size();
    }

    public float averageCost(){
        List<Product> products = productRepository.getProducts();
        float summ = 0.0F;
        float average = 0.0F;
        for (int i = 0; i<products.size(); i++) {
            summ = summ + products.get(i).getCost();
        }
        return average = summ / totalProduct();
    }


    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This product is not exists "+id));
    }
}
