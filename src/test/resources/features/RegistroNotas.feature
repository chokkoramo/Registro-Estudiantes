Feature: Validacion del registro de notas
  Scenario: Registrar notas dentro del rango 0 a 5
    Given Crear estudiante con nombre "Juan" y programa "Ingenieria" despues se le asigna una nota de 3.4
    When Se valida dentro del rango
    Then El sistema registra la nota