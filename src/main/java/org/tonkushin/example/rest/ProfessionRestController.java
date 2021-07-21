package org.tonkushin.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tonkushin.example.model.Profession;
import org.tonkushin.example.service.ProfessionService;

import java.util.List;

/**
 * REST-контроллер для профессий
 */

@RestController
public class ProfessionRestController {
    public static final String URL = "/api/profession/";

    private final ProfessionService service;

    @Autowired
    public ProfessionRestController(ProfessionService service) {
        this.service = service;
    }

    //Возвращает всех
    @GetMapping(URL)
    @ResponseBody
    public List<Profession> getProfessions() {
        return service.findAll();
    }

    //Возвращает по id
    @GetMapping(URL + "{id}")
    public Profession getProfession(@PathVariable(value = "id") Long id) {
        Profession item = new Profession();

        //Редактирование существующей записи
        if (id != null && id != -1) {
            item = service.findOne(id);
        }

        return item;
    }

    @GetMapping(URL + "count/")
    public Long getProfessionsCount() {
        return service.count();
    }

    //Добавляет
    @PostMapping(URL)
    public Profession createProfession(@RequestBody Profession item) {
        return service.save(item);
    }

    //Редактирует
    @PutMapping(URL + "{id}")
    public Profession editProfession(@RequestBody Profession item, @PathVariable String id) {
        return service.save(item);
    }

    //Удаляет
    @DeleteMapping(URL + "{id}")
    public void deleteProfession(@PathVariable Long id) {
        service.delete(id);
    }
}
