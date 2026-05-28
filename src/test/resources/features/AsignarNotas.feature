@2E2
Feature: Asignacion de notas a los estudiantes

  Scenario: Asignar notas exitoso
    Given el usuario hace click en registrar estudiante
    And ingresa el nombre "Juan Carlos" y el programa "Ingenieria de Sistemas"
    And hace click en registrar
    And el sistema debe mostrar un mensaje de confirmacion "¡Estudiante registrado con éxito!"

    When el usuario hace click en Gestionar Estudiantes
    And ingresa el ID "1" en el campo de busqueda
    And ingresa las notas "4.5, 4, 4.5, 1"
    And da click en Guardar Notas
    Then el usuario ve el mensaje de confirmacion "Éxito: Se asignaron 4 notas"