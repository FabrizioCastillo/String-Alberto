package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    private final TareaRepository repository;

    @Value("${app.nombre:TareasApp}")
    private String nombreApp;

    @Value("${app.max-tareas:10}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas:true}")
    private boolean mostrarEstadisticas;


    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    /**
     * Agrega una nueva tarea, validando el límite.
     */
    public Tarea agregarTarea(String descripcion, Prioridad prioridad) {
        List<Tarea> tareasActuales = repository.obtenerTodas();

        // Validación de max-tareas
        if (tareasActuales.size() >= maxTareas) {
            System.err.println("Error: Se ha alcanzado el límite máximo de tareas (" + maxTareas + ")");
            return null; // O lanzar una excepción
        }

        Tarea nuevaTarea = new Tarea(descripcion, prioridad);
        return repository.guardar(nuevaTarea);
    }

    public List<Tarea> listarTodas() {
        return repository.obtenerTodas();
    }

    public List<Tarea> listarPendientes() {
        return repository.obtenerTodas().stream()
                .filter(t -> !t.isCompletada())
                .toList();
    }

    public List<Tarea> listarCompletadas() {
        return repository.obtenerTodas().stream()
                .filter(Tarea::isCompletada)
                .toList();
    }

    public Tarea marcarComoCompletada(Long id) {
        Optional<Tarea> tareaOpt = repository.buscarPorId(id);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.setCompletada(true);
            return tarea;
        }
        return null;
    }

    public String obtenerEstadisticas() {
        if (!mostrarEstadisticas) {
            return "Las estadísticas están desactivadas en este entorno.";
        }

        List<Tarea> todas = repository.obtenerTodas();
        long total = todas.size();
        long completadas = todas.stream().filter(Tarea::isCompletada).count();
        long pendientes = total - completadas;

        return String.format("Total: %d, Completadas: %d, Pendientes: %d", total, completadas, pendientes);
    }

    /**
     * Imprime las propiedades de configuración.
     */
    public void imprimirConfiguracion() {
        System.out.println("--- CONFIGURACIÓN DE LA APP ---");
        System.out.println("Nombre: " + nombreApp);
        System.out.println("Max Tareas: " + maxTareas);
        System.out.println("Mostrar Stats: " + mostrarEstadisticas);
        System.out.println("---------------------------------");
    }
}