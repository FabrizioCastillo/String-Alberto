package com.example.demo.gestioncursos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String matricula;

    // "mappedBy" indica que la relación es gestionada por la entidad Curso.
    // Usamos @JsonIgnore para evitar la serialización recursiva.
    @ManyToMany(mappedBy = "estudiantes")
    @JsonIgnore
    private List<Curso> cursos;
}