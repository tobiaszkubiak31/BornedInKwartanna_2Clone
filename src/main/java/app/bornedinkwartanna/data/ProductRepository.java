package app.bornedinkwartanna.data;

import app.bornedinkwartanna.domain.Product;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findById(Integer id);

    @Transactional
    void deleteById(Integer id);

}
