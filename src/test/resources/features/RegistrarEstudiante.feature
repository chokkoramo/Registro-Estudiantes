@2E2
Feature: Gestion de Estudiantes
  Scenario: Registro exitoso de un nuevo estudiante
    Given el usuario hace click en registrar estudiante
    And ingresa el nombre "Juan Carlos" y el programa "Ingenieria de Sistemas"
    And hace click en registrar
    Then el sistema debe mostrar un mensaje de confirmacion "¡Estudiante registrado con éxito!"
