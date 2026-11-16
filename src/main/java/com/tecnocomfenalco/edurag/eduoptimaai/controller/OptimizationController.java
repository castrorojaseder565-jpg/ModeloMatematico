package com.tecnocomfenalco.edurag.eduoptimaai.controller;

import com.tecnocomfenalco.edurag.eduoptimaai.dto.OptimizationResponse;
import com.tecnocomfenalco.edurag.eduoptimaai.service.OptimizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;

@Controller
public class OptimizationController {

    private final OptimizationService optimizationService;

    public OptimizationController(OptimizationService optimizationService) {
        this.optimizationService = optimizationService;
    }

    @GetMapping("/optimize")
    public String optimize(Model model) {
        try {
            OptimizationResponse response = optimizationService.optimize();
            model.addAttribute("response", response);
            model.addAttribute("assignments", response.getAssignments());
        } catch (RestClientException ex) {
            // Mejora sobre la guía: mensaje claro si FastAPI no está disponible
            model.addAttribute("error",
                    "No se pudo contactar el microservicio de optimización (¿FastAPI está corriendo en el puerto 8000?)");
        }
        return "optimization/result";
    }
}
