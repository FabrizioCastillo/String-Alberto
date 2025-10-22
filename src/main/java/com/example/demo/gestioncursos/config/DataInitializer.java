package com.example.demo.gestioncursos.config;

import com.example.demo.gestioncursos.model.Curso;
import com.example.demo.gestioncursos.model.Estudiante;
import com.example.demo.gestioncursos.model.Profesor;
import com.example.demo.gestioncursos.repository.CursoRepository;
import com.example.demo.gestioncursos.repository.EstudianteRepository;
import com.example.demo.gestioncursos.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Solo inicializar datos si no existen
        if (profesorRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        // Crear profesores
        Profesor profesor1 = new Profesor();
        profesor1.setNombre("Dr. Juan Pérez");
        profesor1.setEmail("juan.perez@universidad.edu");
        profesor1 = profesorRepository.save(profesor1);

        Profesor profesor2 = new Profesor();
        profesor2.setNombre("Dra. María García");
        profesor2.setEmail("maria.garcia@universidad.edu");
        profesor2 = profesorRepository.save(profesor2);

        Profesor profesor3 = new Profesor();
        profesor3.setNombre("Dr. Carlos López");
        profesor3.setEmail("carlos.lopez@universidad.edu");
        profesor3 = profesorRepository.save(profesor3);

        // Crear estudiantes
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setNombre("Ana Rodríguez");
        estudiante1.setMatricula("2024001");
        estudiante1 = estudianteRepository.save(estudiante1);

        Estudiante estudiante2 = new Estudiante();
        estudiante2.setNombre("Luis Martínez");
        estudiante2.setMatricula("2024002");
        estudiante2 = estudianteRepository.save(estudiante2);

        Estudiante estudiante3 = new Estudiante();
        estudiante3.setNombre("Sofia Herrera");
        estudiante3.setMatricula("2024003");
        estudiante3 = estudianteRepository.save(estudiante3);

        Estudiante estudiante4 = new Estudiante();
        estudiante4.setNombre("Diego Torres");
        estudiante4.setMatricula("2024004");
        estudiante4 = estudianteRepository.save(estudiante4);

        Estudiante estudiante5 = new Estudiante();
        estudiante5.setNombre("Valentina Silva");
        estudiante5.setMatricula("2024005");
        estudiante5 = estudianteRepository.save(estudiante5);

        // Crear cursos
        Curso curso1 = new Curso();
        curso1.setNombre("Programación Java");
        curso1.setProfesor(profesor1);
        curso1.setEstudiantes(new HashSet<>(Arrays.asList(estudiante1, estudiante2, estudiante3)));
        curso1 = cursoRepository.save(curso1);

        Curso curso2 = new Curso();
        curso2.setNombre("Base de Datos");
        curso2.setProfesor(profesor2);
        curso2.setEstudiantes(new HashSet<>(Arrays.asList(estudiante2, estudiante4, estudiante5)));
        curso2 = cursoRepository.save(curso2);

        Curso curso3 = new Curso();
        curso3.setNombre("Arquitectura de Software");
        curso3.setProfesor(profesor3);
        curso3.setEstudiantes(new HashSet<>(Arrays.asList(estudiante1, estudiante3, estudiante5)));
        curso3 = cursoRepository.save(curso3);

        Curso curso4 = new Curso();
        curso4.setNombre("Algoritmos y Estructuras de Datos");
        curso4.setProfesor(profesor1);
        curso4.setEstudiantes(new HashSet<>(Arrays.asList(estudiante1, estudiante4)));
        curso4 = cursoRepository.save(curso4);

        System.out.println("=== DATOS DE PRUEBA INICIALIZADOS ===");
        System.out.println("Profesores creados: " + profesorRepository.count());
        System.out.println("Estudiantes creados: " + estudianteRepository.count());
        System.out.println("Cursos creados: " + cursoRepository.count());
        System.out.println("=====================================");
    }
}



