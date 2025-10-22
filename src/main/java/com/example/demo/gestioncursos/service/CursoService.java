package com.example.demo.gestioncursos.service;

import com.example.demo.gestioncursos.model.Estudiante;
import com.example.demo.gestioncursos.model.Curso;
import com.example.demo.gestioncursos.model.Profesor;
import com.example.demo.gestioncursos.repository.CursoRepository;
import com.example.demo.gestioncursos.repository.EstudianteRepository;
import com.example.demo.gestioncursos.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public List<Profesor> getAllProfesores() {
        return profesorRepository.findAll();
    }

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Curso crearCurso(Curso curso, Long profesorId) {
        Profesor profesor = profesorRepository.findById(profesorId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con id: " + profesorId));
        curso.setProfesor(profesor);
        return cursoRepository.save(curso);
    }

    public Curso asignarEstudiante(Long cursoId, Long estudianteId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + cursoId));
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + estudianteId));

        curso.getEstudiantes().add(estudiante);
        return cursoRepository.save(curso);
    }

    public List<Curso> getCursosPorEstudiante(Long estudianteId) {
        if (!estudianteRepository.existsById(estudianteId)) {
            throw new RuntimeException("Estudiante no encontrado con id: " + estudianteId);
        }
        return cursoRepository.findByEstudiantesId(estudianteId);
    }
}