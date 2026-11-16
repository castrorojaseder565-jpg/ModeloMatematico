package com.tecnocomfenalco.edurag.eduoptimaai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El área temática es obligatoria")
    private String topic;                 // Área temática del estudiante

    @Min(value = 1, message = "El riesgo mínimo es 1")
    @Max(value = 5, message = "El riesgo máximo es 5")
    private int academicRisk;             // 1 (bajo) – 5 (crítico)

    @Min(value = 1, message = "La prioridad mínima es 1")
    @Max(value = 10, message = "La prioridad máxima es 10")
    private int priority;                 // 1 (baja) – 10 (máxima)

    // Constructor vacío requerido por JPA
    public Student() {}

    public Student(Long id, String name, String topic, int academicRisk, int priority) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.academicRisk = academicRisk;
        this.priority = priority;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public int getAcademicRisk() { return academicRisk; }
    public void setAcademicRisk(int academicRisk) { this.academicRisk = academicRisk; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
}
