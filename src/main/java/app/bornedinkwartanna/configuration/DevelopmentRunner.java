package app.bornedinkwartanna.configuration;

import app.bornedinkwartanna.configuration.csv.ProductCsvReader;
import app.bornedinkwartanna.configuration.csv.StateCsvReader;
import app.bornedinkwartanna.data.ProductRepository;
import app.bornedinkwartanna.data.StateRepository;
import app.bornedinkwartanna.domain.Product;

import app.bornedinkwartanna.domain.State;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
public class DevelopmentRunner {
    private String getPathToThis() {
        return this.getClass().getName().replace(".", "/") + ".java";
    }

    @Bean
    public CommandLineRunner dataLoader(
        ProductRepository productRepository, StateRepository stateRepository) { // user repo for ease of testing with a built-in user
        return args -> {
            System.out.println(getPathToThis());
            ProductCsvReader productCsvReader = new ProductCsvReader();
            List<Product> products = productCsvReader.read("products.csv");
            for (int i = 0; i < products.size(); i++) {
                products.get(i).setSrc_image(new String(Files.readAllBytes(Paths.get( "img"+ i + ".txt"))));
                productRepository.save(products.get(i));
            }


            StateCsvReader stateCsvReader = new StateCsvReader();
            List<State> states = stateCsvReader.read("states.csv");
            for (State state : states) {
                stateRepository.save(state);
            }

        };

    }

}
