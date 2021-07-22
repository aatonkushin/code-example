package org.tonkushin.example.rest;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get personal")
    @GetMapping(URL)
    @ResponseBody
    public List<Person> getPersons() {
        return service.findAll();
    }

    //Возвращает по id
    @Operation(summary = "Get person by id")
    @GetMapping(URL + "{id}")
    public Person getPerson(@PathVariable("id") Long id) {
        Person item = new Person();

        if (id != null && id != -1) {
            //Редактирование существующей записи
            item = service.findOne(id);
        }

        return item;
    }

    @Operation(summary = "Get count of all persons")
    @GetMapping(URL + "count/")
    public Long getPersonalCount() {
        return service.count();
    }

    //Добавляет
    @Operation(summary = "Create person")
    @PostMapping(URL)
    public Person createPerson(@RequestBody Person item) {
        return service.save(item);
    }

    //Редактирует
    @Operation(summary = "Edit person")
    @PutMapping(URL + "{id}")
    public Person editPerson(@RequestBody Person item, @PathVariable String id) {
        return service.save(item);
    }

    //Удаляет
    @Operation(summary = "Delete person")
    @DeleteMapping(URL + "{id}")
    public void deletePerson(@PathVariable Long id) {
        service.delete(id);
    }
}
