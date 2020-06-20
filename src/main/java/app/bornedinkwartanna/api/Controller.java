package app.bornedinkwartanna.api;

import app.bornedinkwartanna.data.ProductRepository;

import app.bornedinkwartanna.data.StateRepository;
import app.bornedinkwartanna.domain.Product;
import app.bornedinkwartanna.domain.State;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    private ProductRepository productRepository;

    private StateRepository stateRepository;

    @Autowired
    public Controller(ProductRepository productRepository, StateRepository stateRepository){
        this.productRepository = productRepository;
        this.stateRepository = stateRepository;
    }

    @GetMapping(value = "/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }


    @GetMapping(value = "/states")
    public List<State> getStates() {
        return stateRepository.findAll();
    }



}
