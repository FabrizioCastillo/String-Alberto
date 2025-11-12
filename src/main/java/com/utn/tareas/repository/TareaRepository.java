package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TareaRepository {

    private final List<Tarea> tareas = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    public TareaRepository() {
        guardar(new Tarea("Estudiar n8n", Prioridad.ALTA));
        guardar(new Tarea("Hacer el TP de Programación III", Prioridad.ALTA));
        guardar(new Tarea("Ir al Super", Prioridad.MEDIA));
        guardar(new Tarea("Limpiar la casa", Prioridad.BAJA));
    }

    /**
     * Guarda una nueva tarea. Genera un ID automático.
     */
    public Tarea guardar(Tarea tarea) {
        tarea.setId(contadorId.getAndIncrement());
        tareas.add(tarea);
        return tarea;
    }

    /**
     * Retorna una copia de todas las tareas.
     */
    public List<Tarea> obtenerTodas() {
        return new ArrayList<>(tareas);
    }

    /**
     * Busca una tarea por su ID.
     */
    public Optional<Tarea> buscarPorId(Long id) {
        return tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    /**
     * Elimina una tarea por su ID.
     */
    public void eliminarPorId(Long id) {
        tareas.removeIf(t -> t.getId().equals(id));
    }
}