package com.example.demo.gestioncursos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    // Un profesor puede tener muchos cursos.
    // Usamos JsonIgnoreProperties para evitar un bucle infinito al serializar a JSON.
    @OneToMany(mappedBy = "profesor")
    @JsonIgnoreProperties("profesor")
    private List<Curso> cursos;
}