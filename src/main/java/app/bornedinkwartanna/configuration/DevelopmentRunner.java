package app.bornedinkwartanna.configuration;

import app.bornedinkwartanna.configuration.csv.ProductCsvReader;
import app.bornedinkwartanna.configuration.csv.StateCsvReader;
import app.bornedinkwartanna.data.ProductRepository;
import app.bornedinkwartanna.data.StateRepository;
import app.bornedinkwartanna.domain.Product;

import app.bornedinkwartanna.domain.State;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
public class DevelopmentRunner {

    public static final String CSV_LOCATION_PATH = "src/main/java/app/bornedinkwartanna/configuration/csv";

    @Bean
    public CommandLineRunner dataLoader(
        ProductRepository productRepository, StateRepository stateRepository) { // user repo for ease of testing with a built-in user
        return args -> {

            System.out.println( new File("").getAbsolutePath());
            ProductCsvReader productCsvReader = new ProductCsvReader();
            List<Product> products = productCsvReader.read(CSV_LOCATION_PATH + "/products.csv");
            for (int i = 0; i < products.size(); i++) {
                products.get(i).setSrc_image(new String(Files.readAllBytes(Paths.get("src/main/resources/images/img" + i + ".txt"))));
                productRepository.save(products.get(i));
            }


            StateCsvReader stateCsvReader = new StateCsvReader();
            List<State> states = stateCsvReader.read(CSV_LOCATION_PATH + "\\states.csv");
            for (State state : states) {
                stateRepository.save(state);
            }

        };

    }

}
