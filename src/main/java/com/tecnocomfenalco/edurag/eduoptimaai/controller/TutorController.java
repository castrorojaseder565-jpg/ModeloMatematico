package com.tecnocomfenalco.edurag.eduoptimaai.controller;

import com.tecnocomfenalco.edurag.eduoptimaai.model.Tutor;
import com.tecnocomfenalco.edurag.eduoptimaai.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tutors")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tutors", tutorService.findAll());
        return "tutors/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutors/form";
    }

    @PostMapping("/save")
    public String saveTutor(@Valid @ModelAttribute("tutor") Tutor tutor,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "tutors/form";          // Re-muestra el formulario con errores
        }
        tutorService.save(tutor);
        return "redirect:/tutors";         // Patrón POST-Redirect-GET
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Tutor tutor = tutorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tutor no encontrado: " + id));
        model.addAttribute("tutor", tutor);
        return "tutors/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        tutorService.deleteById(id);
        return "redirect:/tutors";
    }
}
