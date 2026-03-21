Feature: Validar funcionamiento del promedio y estado estudiante
  Scenario: Calcular promedio correcto
    Given tres notas de 4.0, 3.5 y 4.5
    When se suman y dividen por el numero de notas (3)
    Then el resultado debe ser 4.0

  Scenario: Verifica si el promedio es menor a 3.0 el estado del estudiante es "REPROBADO"
    Given promedio de 2.3
    When se valida que el promedio sea menor que 3.0
    Then el estado del estudiante debe ser "REPROBADO"