package org.tonkushin.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tonkushin.example.service.DepartmentService;
import org.tonkushin.example.service.PersonService;
import org.tonkushin.example.service.ProfessionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    private final ProfessionService professionService;
    private final DepartmentService departmentService;
    private final PersonService personService;

    public IndexController(ProfessionService professionService, DepartmentService departmentService, PersonService personService) {
        this.professionService = professionService;
        this.departmentService = departmentService;
        this.personService = personService;
    }

    @GetMapping("/")
    public String list(HttpServletRequest request, Model model) {
        model.addAttribute("professionsCount", professionService.count());
        model.addAttribute("departmentsCount", departmentService.count());
        model.addAttribute("personCount", personService.count());
        return "index";
    }
}
