package app.bornedinkwartanna.configuration.csv;

import app.bornedinkwartanna.domain.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.*;

public class ProductCsvReader implements CsvReaderInterface {

    private static final String WORD_SEPARATOR = ",";

    public List<Product> read(String inputFilePath) {
        List<Product> products = new ArrayList<>();
        try{
            File inputF = new File(inputFilePath);
            InputStream inputStream = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            // skip the header of the csv
            products = br.lines().skip(1).map(mapToObject).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }


    private Function<String, Product> mapToObject = (line) -> {
        String[] elements = line.split(WORD_SEPARATOR);

        return Product.builder()
            .product(elements[0])
            .category(elements[1])
            .wholesale_price(Double.valueOf(elements[2]))
            .build();
    };
};
