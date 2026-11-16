package com.tecnocomfenalco.edurag.eduoptimaai.dto;

import java.util.List;

public class OptimizationResponse {
    private String status;                       // 'Optimal', 'Infeasible', etc.
    private double objectiveValue;               // Valor total de la función objetivo
    private List<AssignmentResult> assignments;  // Asignaciones realizadas

    public OptimizationResponse() {}

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getObjectiveValue() { return objectiveValue; }
    public void setObjectiveValue(double objectiveValue) { this.objectiveValue = objectiveValue; }
    public List<AssignmentResult> getAssignments() { return assignments; }
    public void setAssignments(List<AssignmentResult> assignments) { this.assignments = assignments; }
}
