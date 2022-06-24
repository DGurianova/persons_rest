package repository;

import entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    public List<Person> findAllByNameIgnoreCase(String name);

    public List<Person> findAllByLastnameIgnoreCase(String lastname);

    public List<Person> findAllByNameIgnoreCaseAndLastnameIgnoreCase(String name, String lastname);
}

