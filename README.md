# 📚 Registro de Estudiantes API

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Framework-brightgreen)
![Gradle](https://img.shields.io/badge/Gradle-Kotlin%20DSL-02303A?logo=gradle)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?logo=docker)
![Azure DevOps](https://img.shields.io/badge/Azure%20DevOps-CI%2FCD-blue?logo=azuredevops)

[![Build Status](https://dev.azure.com/Chokkoramo/Registro%20Estudiantes/_apis/build/status%2Fchokkoramo.Registro-Estudiantes?branchName=master)](https://dev.azure.com/Chokkoramo/Registro%20Estudiantes/_build/latest?definitionId=1&branchName=master)
![Tests](https://img.shields.io/badge/tests-passing-success)
![Coverage](https://img.shields.io/badge/coverage-80%25-yellowgreen)
![Mutation Testing](https://img.shields.io/badge/mutation-tested-blueviolet)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=chokkoramo_Registro-Estudiantes&metric=alert_status)

API REST desarrollada con **Java 21** y **Spring Boot**, orientada a la gestión de estudiantes y sus notas, aplicando buenas prácticas de desarrollo, pruebas automatizadas y aseguramiento de calidad mediante integración continua.

---

## 🚀 Características

El sistema permite:

- Registrar estudiantes  
- Asignar varias notas al mismo tiempo 
- Calcular el promedio académico  
- Generar ranking de estudiantes  
- Consultar estudiantes registrados  

Actualmente, la información se maneja **en memoria** (sin base de datos).

---

## 🛠️ Tecnologías utilizadas

- Java 21  
- Spring Boot  
- Gradle (Kotlin DSL)  
- Docker  
- Azure DevOps
- Grafana / Influx

---

## 🔄 Integración Continua (CI/CD)

El proyecto cuenta con un pipeline configurado en **Azure DevOps**, el cual se ejecuta utilizando un **agente local en Docker**.

### El pipeline incluye:

- Compilación del proyecto  
- Ejecución de pruebas unitarias  
- Análisis de calidad de código  
- Pruebas de mutación
- Pruebas de Estres
- Generación de reportes  

---

## 🧪 Pruebas implementadas

El proyecto incorpora múltiples niveles de pruebas para garantizar la calidad del software:

### ✔️ Pruebas unitarias
Realizadas con JUnit para validar la lógica del sistema.

### ✔️ Pruebas de comportamiento (BDD)
Implementadas con Cucumber, utilizando escenarios en Gherkin.

### ✔️ Pruebas de calidad
Integración con herramientas de análisis estático como SonarQube.

### ✔️ Pruebas de mutación
Uso de herramientas como PIT (Pitest) para evaluar la robustez de las pruebas.

### ✔️ Pruebas de Estres
Se usa un plan de pruebas de JMeter para evaluar los tiempos de respuesta, numero de usuarios activos y la tasa de error en las consultas

---

## 🐳 Uso de Docker

Se utiliza Docker para:

- Ejecutar el agente de Azure DevOps
- Ejecutar Grafana / influxDB  
- Garantizar entornos reproducibles  
- Facilitar la integración continua  

---

## 📁 Estructura del proyecto

El proyecto sigue una arquitectura típica de Spring Boot:

- `controller` → Manejo de endpoints REST  
- `service` → Lógica de negocio  
- `model` → Entidades del sistema  
- `repository` → Persistencia (en memoria)  
- `test` → Pruebas unitarias y de integración  

---

## 📌 Estado del proyecto

Proyecto en evolución, con enfoque en:

- Mejora continua de calidad  
- Automatización de procesos  
- Escalabilidad futura (posible integración con base de datos y despliegue en la nube)

---

## 📈 Mejoras futuras

- Integración con base de datos (PostgreSQL/MySQL)  
- Despliegue en la nube  
- Autenticación y autorización (Spring Security)  
- Documentación con Swagger/OpenAPI  
