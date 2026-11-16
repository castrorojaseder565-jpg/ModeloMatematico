package com.tecnocomfenalco.edurag.eduoptimaai.controller;

import com.tecnocomfenalco.edurag.eduoptimaai.model.Student;
import com.tecnocomfenalco.edurag.eduoptimaai.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "students/form";          // Re-muestra el formulario con errores
        }
        studentService.save(student);
        return "redirect:/students";         // Patrón POST-Redirect-GET
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado: " + id));
        model.addAttribute("student", student);
        return "students/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }
}
