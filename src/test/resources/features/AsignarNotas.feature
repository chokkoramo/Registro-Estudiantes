@2E2
Feature: Asignacion de notas a los estudiantes
  Scenario: Asignar notas exitoso
    Given que el usuario esta en el inicio
    When el usuario hace click en Gestionar Estudiantes
    And ingresa el ID "3" en el campo de busqueda
    And ingresa las notas "4.5, 4, 4.5, 1"
    And da click en Guardar Notas
    Then el usuario ve el mensaje de confirmacion "Éxito: Se asignaron 4 notas"

