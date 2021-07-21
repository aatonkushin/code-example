package org.tonkushin.example.rest;

import org.springframework.web.bind.annotation.*;
import org.tonkushin.example.model.Person;
import org.tonkushin.example.service.PersonService;

import java.util.List;

/**
 * REST-контроллер для сотрудников
 */

@RestController
public class PersonRestController {
    public static final String URL = "/api/person/";

    private final PersonService service;

    public PersonRestController(PersonService service) {
        this.service = service;
    }

    //Возвращает всех
    @GetMapping(URL)
    @ResponseBody
    public List<Person> getPersons() {
        return service.findAll();
    }

    //Возвращает по id
    @GetMapping(URL + "{id}")
    public Person getPerson(@PathVariable("id") Long id) {
        Person item = new Person();

        if (id != null && id != - 1) {
            //Редактирование существующей записи
            item = service.findOne(id);
        }

        return item;
    }

    @GetMapping(URL+"count/")
    public Long getPersonalCount(){
        return service.count();
    }

    //Добавляет
    @PostMapping(URL)
    Person createPerson(@RequestBody Person item) {
        return service.save(item);
    }

    //Редактирует
    @PutMapping(URL + "{id}")
    Person editPerson(@RequestBody Person item, @PathVariable String id) {
        return service.save(item);
    }

    //Удаляет
    @DeleteMapping(URL + "{id}")
    void deletePerson(@PathVariable Long id) {
        service.delete(id);
    }
}
