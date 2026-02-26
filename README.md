# Sistema de Registro de Estudiantes

##  Descripción

Este proyecto consiste en una API REST desarrollada en Java con Spring Boot que permite gestionar estudiantes de manera básica.

El sistema permite:

- Registrar estudiantes
- Asignar notas
- Calcular promedio
- Generar ranking académico
- Consultar estudiantes registrados

Actualmente la información se almacena en memoria (sin base de datos).

---

##  Arquitectura Actual

El sistema fue construido con fines académicos y contiene decisiones de diseño que permiten posteriormente aplicar refactorización y mejoras de calidad.

Características actuales:

- Almacenamiento en memoria usando `ArrayList`
- Lógica concentrada en una sola clase principal (`SistemaAcademico`)
- Sin separación por capas (Controller, Service, Repository)
- Sin persistencia de datos

---

##  Problemas y Vulnerabilidades Identificadas

El sistema presenta varios problemas detectables mediante análisis estático (por ejemplo, SonarQube):

1. Retorno de `null` en métodos de búsqueda (riesgo de `NullPointerException`).
2. Exposición directa de estructuras internas mutables (`List`).
3. Alta complejidad cognitiva en algunos métodos.
4. Falta de validación de parámetros.
5. Ausencia de manejo adecuado de excepciones.
6. Posible código duplicado en cálculos de promedio.
7. Falta de encapsulamiento estricto.
8. No se aplican principios SOLID.

---

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Gradle
- API REST

---
