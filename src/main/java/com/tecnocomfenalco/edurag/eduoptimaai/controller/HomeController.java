package com.tecnocomfenalco.edurag.eduoptimaai.controller;

import com.tecnocomfenalco.edurag.eduoptimaai.service.StudentService;
import com.tecnocomfenalco.edurag.eduoptimaai.service.TutorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final StudentService studentService;
    private final TutorService tutorService;

    public HomeController(StudentService studentService, TutorService tutorService) {
        this.studentService = studentService;
        this.tutorService = tutorService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("studentCount", studentService.findAll().size());
        model.addAttribute("tutorCount", tutorService.findAll().size());
        return "index";
    }
}
