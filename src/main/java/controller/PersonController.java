package controller;


import entity.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //get the person by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Person get(@PathVariable Integer id) {
        return personService.get(id);
    }

    //create a new person
    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.save(person);
    }

    //make changes/edit person
    @PutMapping
    public void edit(@RequestBody Person person) {
        personService.save(person);
    }

    //delete person
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        personService.remove(id);
    }

    @GetMapping
    public List<Person> getAll(@RequestParam(required = false) Optional<String> name,
                               @RequestParam(required = false) Optional<String> lastname) {
        return personService.getAllByNameAndLastname(name, lastname);
    }
}
