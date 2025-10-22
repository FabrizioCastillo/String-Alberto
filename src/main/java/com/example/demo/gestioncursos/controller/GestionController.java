package com.example.demo.gestioncursos.controller;

import com.example.demo.gestioncursos.model.Curso;
import com.example.demo.gestioncursos.model.Estudiante;
import com.example.demo.gestioncursos.model.Profesor;
import com.example.demo.gestioncursos.repository.CursoRepository;
import com.example.demo.gestioncursos.repository.EstudianteRepository;
import com.example.demo.gestioncursos.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GestionController {

    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private CursoRepository cursoRepository;

    // --- Endpoints para LISTAR ---

    @GetMapping("/cursos")
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @GetMapping("/profesores")
    public List<Profesor> listarProfesores() {
        return profesorRepository.findAll();
    }

    @GetMapping("/estudiantes")
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    // --- Endpoint para CREAR Curso con Profesor ---
    @PostMapping("/cursos")
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {

        Profesor profesor = profesorRepository.findById(curso.getProfesor().getId())
                .orElseThrow(() -> new RuntimeException("Error: Profesor no encontrado con id " + curso.getProfesor().getId()));

        curso.setProfesor(profesor);
        Curso nuevoCurso = cursoRepository.save(curso);
        return ResponseEntity.ok(nuevoCurso);
    }

    // --- Endpoint para ASIGNAR un Estudiante a un Curso ---
    @PostMapping("/cursos/{cursoId}/estudiantes/{estudianteId}")
    public ResponseEntity<Curso> asignarEstudianteACurso(@PathVariable Long cursoId, @PathVariable Long estudianteId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Error: Curso no encontrado con id " + cursoId));

        // LÍNEA 63 CORREGIDA: Usar findById en lugar de existsById
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Error: Estudiante no encontrado con id " + estudianteId));

        curso.getEstudiantes().add(estudiante);
        cursoRepository.save(curso);
        return ResponseEntity.ok(curso);
    }

    // --- Endpoint para DEVOLVER los Cursos de un Estudiante ---
    @GetMapping("/estudiantes/{estudianteId}/cursos")
    public ResponseEntity<List<Curso>> obtenerCursosDeEstudiante(@PathVariable Long estudianteId) {
        if (!estudianteRepository.existsById(estudianteId)) {
            // Aquí sí es correcto usar existsById, porque solo queremos verificar si existe.
            throw new RuntimeException("Error: Estudiante no encontrado con id " + estudianteId);
        }
        List<Curso> cursos = cursoRepository.findByEstudiantesId(estudianteId);
        return ResponseEntity.ok(cursos);
    }

    // --- Endpoints adicionales útiles ---

    // Crear un nuevo profesor
    @PostMapping("/profesores")
    public ResponseEntity<Profesor> crearProfesor(@RequestBody Profesor profesor) {
        Profesor nuevoProfesor = profesorRepository.save(profesor);
        return ResponseEntity.ok(nuevoProfesor);
    }

    // Crear un nuevo estudiante
    @PostMapping("/estudiantes")
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteRepository.save(estudiante);
        return ResponseEntity.ok(nuevoEstudiante);
    }

    // Obtener un curso específico por ID
    @GetMapping("/cursos/{cursoId}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Error: Curso no encontrado con id " + cursoId));
        return ResponseEntity.ok(curso);
    }

    // Obtener un profesor específico por ID
    @GetMapping("/profesores/{profesorId}")
    public ResponseEntity<Profesor> obtenerProfesor(@PathVariable Long profesorId) {
        Profesor profesor = profesorRepository.findById(profesorId)
                .orElseThrow(() -> new RuntimeException("Error: Profesor no encontrado con id " + profesorId));
        return ResponseEntity.ok(profesor);
    }

    // Obtener un estudiante específico por ID
    @GetMapping("/estudiantes/{estudianteId}")
    public ResponseEntity<Estudiante> obtenerEstudiante(@PathVariable Long estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Error: Estudiante no encontrado con id " + estudianteId));
        return ResponseEntity.ok(estudiante);
    }

    // Obtener todos los estudiantes de un curso específico
    @GetMapping("/cursos/{cursoId}/estudiantes")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantesDeCurso(@PathVariable Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Error: Curso no encontrado con id " + cursoId));
        return ResponseEntity.ok(curso.getEstudiantes().stream().toList());
    }

    // Obtener todos los cursos de un profesor específico
    @GetMapping("/profesores/{profesorId}/cursos")
    public ResponseEntity<List<Curso>> obtenerCursosDeProfesor(@PathVariable Long profesorId) {
        Profesor profesor = profesorRepository.findById(profesorId)
                .orElseThrow(() -> new RuntimeException("Error: Profesor no encontrado con id " + profesorId));
        return ResponseEntity.ok(profesor.getCursos());
    }
}