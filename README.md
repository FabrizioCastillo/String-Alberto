# Gestor de Tareas - TP Programación III (UTN)

Este proyecto es un Sistema de Gestión de Tareas (To\-Do List) desarrollado como Trabajo Práctico para la materia Programación III de la Tecnicatura Universitaria en Programación (UTN).

El objetivo principal es aplicar los conceptos fundamentales de Spring Boot: Inyección de Dependencias, estereotipos (\`@Service\`, \`@Repository\`), configuración externa con archivos \`*.properties\` y gestión de entornos mediante Profiles.

## 🚀 Tecnologías utilizadas

- Java 21  
- Spring Boot 3.5.7  
- Apache Maven  
- Lombok

## 📋 Instrucciones para clonar y ejecutar

1. Prerrequisitos
   - Git instalado.
   - JDK 17 o superior (se usa Java 21).
   - Apache Maven instalado.

2. Clonar el repositorio

    cd hasta la carpeta deseada e iniciar clonación:

    git clone https://github.com/FabrizioCastillo/String-Alberto  
    cd tareas

3. Ejecutar la aplicación con Maven

   Ejecutar en modo dev (perfil por defecto según \`src/main/resources/application.properties\`):

    mvn spring-boot:run

   Ejecutar en modo prod (activar perfil prod):

    mvn spring-boot:run -Dspring.profiles.active=prod

## ⚙️ Gestión de perfiles (Profiles)

La aplicación usa perfiles de Spring para comportamientos distintos:

- dev (Desarrollo):
  - Mensajes de bienvenida y estadísticas detalladas.
  - Límite máximo de tareas: 10.
  - Nivel de logging: DEBUG.

- prod (Producción):
  - Mensajes de consola simples.
  - Límite máximo de tareas: 1000.
  - Estadísticas desactivadas por defecto.
  - Nivel de logging: ERROR.

Cómo cambiar el perfil activo:
- Modificar \`src/main/resources/application.properties\` y cambiar \`spring.profiles.active=dev\` por \`prod\`.
- O pasar la propiedad al ejecutar: \`-Dspring.profiles.active=prod\` (recomendado).

## 🖥️ Salida de consola (ejemplos)

Ejecución en perfil \`dev\` (resumen):

    2025-11-12T16:08:40.091-03:00  INFO ... The following 1 profile is active: "dev"
    ...
    ******************************************
    * BIENVENIDO AL GESTOR DE TAREAS (DEV) *
    * >> Entorno de Desarrollo Activo <<     *
    ******************************************
    --- CONFIGURACIÓN DE LA APP ---
    Nombre: Gestor de Tareas UTN
    Max Tareas: 10
    Mostrar Stats: true
    ---------------------------------
    ...
    --- 3. TAREAS INICIALES ---
    Tarea{id=1, descripcion='Estudiar Spring Boot', prioridad=ALTA, completada=false}
    ...
    --- 7. ESTADÍSTICAS ---
    Total: 5, Completadas: 1, Pendientes: 4
    ...
    ******************************************
    * Cerrando aplicación...       *
    * ¡Hasta luego DEV!         *
    ******************************************

Ejecución en perfil \`prod\` (resumen):

    2025-11-12T16:15:44.647-03:00  INFO ... The following 1 profile is active: "prod"
    ...
    === Bienvenido al Gestor de Tareas ===
    --- CONFIGURACIÓN DE LA APP ---
    Nombre: Gestor de Tareas UTN
    Max Tareas: 1000
    Mostrar Stats: false
    ---------------------------------
    ...
    --- 3. TAREAS INICIALES ---
    Tarea{id=1, descripcion='Estudiar Spring Boot', prioridad=ALTA, completada=false}
    ...
    --- 7. ESTADÍSTICAS ---
    Las estadísticas están desactivadas en este entorno.
    ...
    === Aplicación finalizada. ===

## 🧠 Conclusiones personales

AQUÍ DEBES ESCRIBIR TUS CONCLUSIONES PERSONALES SOBRE LO APRENDIDO.

(Ejemplos de ideas a desarrollar):
- Aplicación práctica de Inyección de Dependencias por constructor y desacoplamiento entre \`TareaService\` y \`TareaRepository\`.
- Uso de \`@Profile\` para gestionar comportamiento por entorno.
- Inyección de configuración con \`@Value\` desde \`application.properties\`.
- Uso de \`CommandLineRunner\` para ejecutar la lógica de consola al iniciar la aplicación.

## 👨‍💻 Autor

- Nombre: Fabrizio Castillo
- Legajo: 52617
