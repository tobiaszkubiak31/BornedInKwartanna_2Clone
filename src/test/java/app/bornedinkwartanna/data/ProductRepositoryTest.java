package app.bornedinkwartanna.data;

import app.bornedinkwartanna.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    Product sampleProduct1;

    Product sampleProduct2;

    @Before
    public void setUp() {
        sampleProduct1 = Product.builder()
                .product("sampleProduct1")
                .category("sampleCategory1")
                .wholesale_price(0.00)
                .build();
        sampleProduct2 = Product.builder()
                .product("sampleProduct2")
                .category("sampleCategory2")
                .wholesale_price(0.00)
                .build();
    }

    @Test
    public void shouldFindNoProductsIfRepositoryIsEmptyTest() {

        List<Product> products = productRepository.findAll();

        assertThat(products).hasSize(0);
    }

    @Test
    public void shouldFindOneProductIfRepositoryContainsOneProductEntityTest() {
        entityManager.persist(sampleProduct1);
        List<Product> products = productRepository.findAll();

        assertThat(products).hasSize(1);
        assertThat(products.get(0)).
                isEqualTo(sampleProduct1);
    }

    @Test
    public void shouldFindTwoProductsIfRepositoryContainsTwoProductsEntityTest() {
        entityManager.persist(sampleProduct1);
        entityManager.persist(sampleProduct2);
        List<Product> products = productRepository.findAll();

        assertThat(products).hasSize(2);
        assertThat(products.get(0)).
                isEqualTo(sampleProduct1);
        assertThat(products.get(1)).
                isEqualTo(sampleProduct2);
    }

    @Test
    public void shouldStoreANewProductTest() {
        Product persistedProduct = productRepository.save(sampleProduct1);

        assertThat(persistedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void shouldStoreANewProductsTest() {
        Product persistedProduct1 = productRepository.save(sampleProduct1);
        Product persistedProduct2 = productRepository.save(sampleProduct2);

        assertThat(persistedProduct1.getId()).isGreaterThan(0);

        assertThat(persistedProduct2.getId()).isGreaterThan(0);
    }

    @Test
    public void whenFindByProperIdThenReturnProductTest1() {
        Product persistedProduct1 = entityManager.persist(sampleProduct1);
        entityManager.persist(sampleProduct2);

        Product foundProduct = productRepository.findById(persistedProduct1.getId()).orElse(null);

        assertThat(foundProduct).isEqualTo(sampleProduct1);
    }

    @Test
    public void whenFindByProperIdThenReturnProductTest2() {
        entityManager.persist(sampleProduct1);
        Product persistedProduct2 = entityManager.persist(sampleProduct2);

        Product foundProduct = productRepository.findById(persistedProduct2.getId()).orElse(null);

        assertThat(foundProduct).isEqualTo(sampleProduct2);
    }

    @Test
    public void whenFindByInvalidIdThenReturnNullTest() {
        Product persistedProduct1 = entityManager.persist(sampleProduct1);
        entityManager.persist(sampleProduct2);
        final int INVALID_ID = persistedProduct1.getId() + 3;

        Product foundProduct = productRepository.findById(INVALID_ID).orElse(null);

        assertThat(foundProduct).isNull();
    }

    @Test
    public void shouldDeleteProductTest() {
        Product persistedProduct1 = entityManager.persist(sampleProduct1);
        Product persistedProduct2 = entityManager.persist(sampleProduct2);

        productRepository.deleteById(persistedProduct1.getId());

        List<Product> products = productRepository.findAll();

        assertThat(products).doesNotContain(persistedProduct1);

        assertThat(products).contains(persistedProduct2);
    }
}
