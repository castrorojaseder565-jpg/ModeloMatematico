package com.tecnocomfenalco.edurag.eduoptimaai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "La especialidad es obligatoria")
    private String specialty;             // Clave de compatibilidad temática

    @Min(value = 1, message = "La capacidad mínima es 1")
    private int maxCapacity;              // Máximo de estudiantes que puede atender

    public Tutor() {}

    public Tutor(Long id, String name, String specialty, int maxCapacity) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.maxCapacity = maxCapacity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }
}
