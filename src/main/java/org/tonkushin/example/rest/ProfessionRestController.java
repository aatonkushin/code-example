package org.tonkushin.example.rest;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get all professions")
    @GetMapping(URL)
    @ResponseBody
    public List<Profession> getProfessions() {
        return service.findAll();
    }

    //Возвращает по id
    @Operation(summary = "Get profession by its id")
    @GetMapping(URL + "{id}")
    public Profession getProfession(@PathVariable(value = "id") Long id) {
        Profession item = new Profession();

        //Редактирование существующей записи
        if (id != null && id != -1) {
            item = service.findOne(id);
        }

        return item;
    }

    @Operation(summary = "Get count of all professions")
    @GetMapping(URL + "count/")
    public Long getProfessionsCount() {
        return service.count();
    }

    //Добавляет
    @Operation(summary = "Add new profession")
    @PostMapping(URL)
    public Profession createProfession(@RequestBody Profession item) {
        return service.save(item);
    }

    //Редактирует
    @Operation(summary = "Edit profession by its id")
    @PutMapping(URL + "{id}")
    public Profession editProfession(@RequestBody Profession item, @PathVariable Long id) {
        return service.update(item, id);
    }

    //Удаляет
    @Operation(summary = "Delete profession by its id")
    @DeleteMapping(URL + "{id}")
    public void deleteProfession(@PathVariable Long id) {
        service.delete(id);
    }
}
