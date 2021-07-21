package org.tonkushin.example.rest;

import org.springframework.web.bind.annotation.*;
import org.tonkushin.example.model.Department;
import org.tonkushin.example.service.DepartmentService;

import java.util.List;

/**
 * REST-контроллер для подразделений
 */

@RestController
public class DepartmentRestController {
    public static final String URL = "/api/department/";

    private final DepartmentService service;

    public DepartmentRestController(DepartmentService service) {
        this.service = service;
    }

    //Возвращает всех
    @GetMapping(URL)
    @ResponseBody
    public List<Department> getDepartments() {
        return service.findAll();
    }

    //Возвращает по id
    @GetMapping(URL + "{id}")
    public Department getDepartment(@PathVariable("id") Long id) {
        Department item = new Department();

        if (id != null && id != -1) {
            //Редактирование существующей записи
            item = service.findOne(id);

        }

        return item;
    }

    @GetMapping(URL + "count/")
    public Long getDepartmentsCount() {
        return service.count();
    }

    //Добавляет
    @PostMapping(URL)
    public Department createDepartment(@RequestBody Department item) {
        return service.save(item);
    }

    //Редактирует
    @PutMapping(URL + "{id}")
    public Department editDepartment(@RequestBody Department item, @PathVariable String id) {
        return service.save(item);
    }

    //Удаляет
    @DeleteMapping(URL + "{id}")
    public void deleteDepartment(@PathVariable Long id) {
        service.delete(id);
    }
}
