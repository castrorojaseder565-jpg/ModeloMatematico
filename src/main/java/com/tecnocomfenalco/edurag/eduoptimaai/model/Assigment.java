package com.tecnocomfenalco.edurag.eduoptimaai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidad placeholder (fase futura): permitirá guardar el historial de asignaciones óptimas.
 * Por ahora las asignaciones NO se persisten; se muestran en memoria (AssignmentResult).
 */
@Entity
public class Assigment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
