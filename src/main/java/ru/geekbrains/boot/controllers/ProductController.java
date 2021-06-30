package ru.geekbrains.boot.controllers;

import lombok.RequiredArgsConstructor;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.model.Student;
import ru.geekbrains.boot.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping("/all")
    public String getAllProducts(Model model) {
        model.addAttribute("frontProducts", productService.getAllProducts());
        return "all_products";
    }

    @GetMapping("/remove/{id}")
    public String deleteBoxById(@PathVariable int id) {
        productService.deleteById(id);
        return "redirect:/boxes/all";
    }

    @PostMapping("/add")
    public String addNewBox(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products/all";
    }

    @GetMapping("/find")
    @ResponseBody
    public Product getById(@RequestParam Long id){
        return productService.findById(id);
    }
}
