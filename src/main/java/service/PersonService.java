package service;

import entity.Person;
import exceptions.PersonNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service


public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person remove(int id) {
        Person person = get(id);
        personRepository.delete(person);
        return person;
    }


    public Person get(int id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("There is no person with such id"));
    }

    public List<Person> getAllByNameAndLastname(Optional<String> name, Optional<String> lastname) {
        if (name.isPresent() && lastname.isPresent()) {
            return personRepository.findAllByNameIgnoreCaseAndLastnameIgnoreCase(name.get(), lastname.get());
        } else if (name.isPresent()) {
            return personRepository.findAllByNameIgnoreCase(name.get());
        } else if (lastname.isPresent()) {
            return personRepository.findAllByLastnameIgnoreCase(lastname.get());
        }
        return personRepository.findAll();
    }


}
