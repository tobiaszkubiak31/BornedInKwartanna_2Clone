package app.bornedinkwartanna.data;

import app.bornedinkwartanna.domain.State;
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
public class StateRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StateRepository stateRepository;

    State sampleState1;

    State sampleState2;

    @Before
    public void setUp() {
        sampleState1 = State.builder()
                .clothing(0.00)
                .groceries(0.00)
                .intangibles(0.00)
                .logistics(0.00)
                .name("state1")
                .nonPrescriptionDrug(0.00)
                .preparedFood(0.00)
                .prescriptionDrug(0.00)
                .build();
        sampleState2 = State.builder()
                .clothing(1.11)
                .groceries(1.11)
                .intangibles(1.11)
                .logistics(1.11)
                .name("state2")
                .nonPrescriptionDrug(1.11)
                .preparedFood(1.11)
                .prescriptionDrug(1.11)
                .build();
    }

    @Test
    public void shouldFindNoStatesIfRepositoryIsEmptyTest() {

        List<State> states = stateRepository.findAll();

        assertThat(states).hasSize(0);
    }

    @Test
    public void shouldFindOneStateIfRepositoryContainsOneStateEntityTest() {
        entityManager.persist(sampleState1);
        List<State> states = stateRepository.findAll();

        assertThat(states).hasSize(1);
        assertThat(states.get(0)).
                isEqualTo(sampleState1);
    }

    @Test
    public void shouldFindTwoStatesIfRepositoryContainsTwoStatesEntityTest() {
        entityManager.persist(sampleState1);
        entityManager.persist(sampleState2);
        List<State> states = stateRepository.findAll();

        assertThat(states).hasSize(2);
        assertThat(states.get(0)).
                isEqualTo(sampleState1);
        assertThat(states.get(1)).
                isEqualTo(sampleState2);
    }

    @Test
    public void shouldStoreANewStateTest() {
        State persistedState = stateRepository.save(sampleState1);

        assertThat(persistedState.getId()).isGreaterThan(0);
    }

    @Test
    public void shouldStoreANewStatesTest() {
        State persistedState1 = stateRepository.save(sampleState1);
        State persistedState2 = stateRepository.save(sampleState2);

        assertThat(persistedState1.getId()).isGreaterThan(0);

        assertThat(persistedState2.getId()).isGreaterThan(0);
    }

    @Test
    public void whenFindByProperIdThenReturnStateTest1() {
        State persistedState1 = entityManager.persist(sampleState1);
        entityManager.persist(sampleState2);

        State foundState = stateRepository.findById(persistedState1.getId()).orElse(null);

        assertThat(foundState).isEqualTo(sampleState1);
    }

    @Test
    public void whenFindByProperIdThenReturnStateTest2() {
        entityManager.persist(sampleState1);
        State persistedState2 = entityManager.persist(sampleState2);

        State foundState = stateRepository.findById(persistedState2.getId()).orElse(null);

        assertThat(foundState).isEqualTo(sampleState2);
    }

    @Test
    public void whenFindByInvalidIdThenReturnNullTest() {
        State persistedState1 = entityManager.persist(sampleState1);
        entityManager.persist(sampleState2);
        final int INVALID_ID = persistedState1.getId() + 3;

        State foundState = stateRepository.findById(INVALID_ID).orElse(null);

        assertThat(foundState).isNull();
    }

    @Test
    public void shouldDeleteStateTest() {
        State persistedState1 = entityManager.persist(sampleState1);
        State persistedState2 = entityManager.persist(sampleState2);

        stateRepository.deleteById(persistedState1.getId());

        List<State> states = stateRepository.findAll();

        assertThat(states).doesNotContain(persistedState1);

        assertThat(states).contains(persistedState2);
    }
}
