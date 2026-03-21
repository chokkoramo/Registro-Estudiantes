Feature: Validacion del registro de notas
  Scenario: Notas dentro del rango 0 a 5
    Given se ingresa una nota de 3.4
    When se valida
    Then el sistema lo registra