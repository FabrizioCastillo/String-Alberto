# API REST de Gestión de Cursos - Spring Boot

## Descripción
Esta API REST permite gestionar cursos, profesores y estudiantes con las siguientes funcionalidades principales:

- ✅ Listar cursos, profesores y estudiantes
- ✅ Crear nuevos cursos con su profesor
- ✅ Asignar estudiantes a un curso
- ✅ Devolver la lista de cursos en los que está un estudiante

## Tecnologías Utilizadas
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database** (en memoria)
- **Lombok** (para reducir código boilerplate)
- **Jackson** (para serialización JSON)

## Estructura del Proyecto

### Modelos de Datos
- **Profesor**: id, nombre, email
- **Estudiante**: id, nombre, matrícula
- **Curso**: nombre, profesor asociado, lista de estudiantes

### Relaciones
- **Profesor ↔ Curso**: @ManyToOne (un profesor puede tener varios cursos)
- **Curso ↔ Estudiante**: @ManyToMany (un curso puede tener varios estudiantes, un estudiante puede estar en varios cursos)

## Ejemplos de Uso

### 1. Listar todos los cursos
```bash
GET http://localhost:8080/api/cursos
```

### 2. Crear un nuevo profesor
```bash
POST http://localhost:8080/api/profesores
Content-Type: application/json

{
  "nombre": "Dr. María González",
  "email": "maria.gonzalez@universidad.edu"
}
```

### 3. Crear un nuevo curso
```bash
POST http://localhost:8080/api/cursos
Content-Type: application/json

{
  "nombre": "Desarrollo Web",
  "profesor": {
    "id": 1
  }
}
```

### 4. Asignar estudiante a curso
```bash
POST http://localhost:8080/api/cursos/1/estudiantes/2
```

### 5. Obtener cursos de un estudiante
```bash
GET http://localhost:8080/api/estudiantes/1/cursos
```

## Datos de Prueba
La aplicación incluye datos de ejemplo que se cargan automáticamente al iniciar:

### Profesores
- Dr. Juan Pérez (juan.perez@universidad.edu)
- Dra. María García (maria.garcia@universidad.edu)
- Dr. Carlos López (carlos.lopez@universidad.edu)

### Estudiantes
- Ana Rodríguez (matrícula: 2024001)
- Luis Martínez (matrícula: 2024002)
- Sofia Herrera (matrícula: 2024003)
- Diego Torres (matrícula: 2024004)
- Valentina Silva (matrícula: 2024005)

### Cursos
- Programación Java (Profesor: Dr. Juan Pérez)
- Base de Datos (Profesor: Dra. María García)
- Arquitectura de Software (Profesor: Dr. Carlos López)
- Algoritmos y Estructuras de Datos (Profesor: Dr. Juan Pérez)

## Cómo Ejecutar la Aplicación

### Prerrequisitos
- Java 21
- Maven 3.6+

### Pasos
1. Clonar o descargar el proyecto
2. Abrir terminal en la carpeta del proyecto
3. Ejecutar: `mvn spring-boot:run`
4. La aplicación estará disponible en: `http://localhost:8080`

### Consola H2 Database
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuario: `sa`
- Contraseña: (vacía)

## Características Técnicas

### Programación Funcional
El proyecto utiliza características de programación funcional de Java 8+:
- **Streams**: Para procesamiento de colecciones
- **Expresiones Lambda**: Para código más conciso
- **Optional**: Para manejo seguro de valores nulos
- **Referencias a Métodos**: Para mayor legibilidad

### Ejemplo de Uso de Streams
```java
// Obtener estudiantes de un curso usando streams
List<Estudiante> estudiantes = curso.getEstudiantes()
    .stream()
    .filter(estudiante -> estudiante.getMatricula().startsWith("2024"))
    .sorted(Comparator.comparing(Estudiante::getNombre))
    .collect(Collectors.toList());
```

## Estructura de Respuestas JSON

### Curso
```json
{
  "id": 1,
  "nombre": "Programación Java",
  "profesor": {
    "id": 1,
    "nombre": "Dr. Juan Pérez",
    "email": "juan.perez@universidad.edu"
  },
  "estudiantes": [
    {
      "id": 1,
      "nombre": "Ana Rodríguez",
      "matricula": "2024001"
    }
  ]
}
```

### Profesor
```json
{
  "id": 1,
  "nombre": "Dr. Juan Pérez",
  "email": "juan.perez@universidad.edu",
  "cursos": [
    {
      "id": 1,
      "nombre": "Programación Java"
    }
  ]
}
```

### Estudiante
```json
{
  "id": 1,
  "nombre": "Ana Rodríguez",
  "matricula": "2024001"
}
```

## Manejo de Errores
La API devuelve errores HTTP estándar:
- `400 Bad Request`: Datos inválidos
- `404 Not Found`: Recurso no encontrado
- `500 Internal Server Error`: Error interno del servidor

## Consideraciones de Rendimiento
- Base de datos en memoria (H2) para desarrollo
- Lazy loading en relaciones JPA
- Serialización JSON optimizada con Jackson
- Manejo eficiente de colecciones con Streams

## Extensibilidad
El proyecto está diseñado para ser fácilmente extensible:
- Nuevos endpoints pueden agregarse al `GestionController`
- Nuevos modelos pueden crearse siguiendo el patrón existente
- Servicios adicionales pueden implementarse en la capa de servicio
- Validaciones pueden agregarse usando Bean Validation

## Conclusión
Esta API REST implementa completamente los requerimientos especificados, utilizando las mejores prácticas de Spring Boot y programación funcional en Java. La aplicación está lista para uso en desarrollo y puede ser fácilmente adaptada para producción con una base de datos persistente.



