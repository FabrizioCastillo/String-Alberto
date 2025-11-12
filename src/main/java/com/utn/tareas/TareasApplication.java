package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

	private final TareaService tareaService;
	private final MensajeService mensajeService;

	// Inyección por constructor [cite: 119]
	public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
		this.tareaService = tareaService;
		this.mensajeService = mensajeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// --- Flujo de ejecución (Parte 6)

		// 1. Mostrar bienvenida
		mensajeService.mostrarBienvenida();

		// 2. Mostrar configuración
		tareaService.imprimirConfiguracion();

		// 3. Listar tareas iniciales
		System.out.println("\n--- 3. TAREAS INICIALES ---");
		tareaService.listarTodas().forEach(System.out::println);

		// 4. Agregar nueva tarea
		System.out.println("\n--- 4. AGREGAR NUEVA TAREA ---");
		Tarea nueva = tareaService.agregarTarea("Revisar PRs en GitHub", Prioridad.MEDIA);
		if (nueva != null) {
			System.out.println("Tarea agregada: " + nueva);
		}

		// 5. Listar tareas pendientes
		System.out.println("\n--- 5. TAREAS PENDIENTES ---");
		tareaService.listarPendientes().forEach(System.out::println);

		// 6. Marcar tarea como completada (ej: ID 1)
		System.out.println("\n--- 6. COMPLETAR TAREA ID 1 ---");
		Tarea completada = tareaService.marcarComoCompletada(1L);
		System.out.println("Tarea completada: " + completada);

		// 7. Mostrar estadísticas
		System.out.println("\n--- 7. ESTADÍSTICAS ---");
		System.out.println(tareaService.obtenerEstadisticas());

		// 8. Listar tareas completadas
		System.out.println("\n--- 8. TAREAS COMPLETADAS ---");
		tareaService.listarCompletadas().forEach(System.out::println);

		// 9. Mostrar despedida
		System.out.println("\n");
		mensajeService.mostrarDespedida();
	}
}