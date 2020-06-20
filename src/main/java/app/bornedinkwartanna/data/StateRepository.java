package app.bornedinkwartanna.data;

import app.bornedinkwartanna.domain.Product;
import app.bornedinkwartanna.domain.State;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    Optional<State> findById(Integer id);

    @Transactional
    void deleteById(Integer id);

}
