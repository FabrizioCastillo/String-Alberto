package com.example.demo.gestioncursos.repository;

// La corrección clave está en esta línea:
import com.example.demo.gestioncursos.model.Curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
// Al corregir el import, Java ya sabe qué es "Curso" aquí.
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Y también sabe qué es "Curso" aquí.
    List<Curso> findByEstudiantesId(Long estudianteId);
}