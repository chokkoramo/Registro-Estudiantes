Feature: Validar funcionamiento del promedio y estado estudiante

  Scenario: Calcular promedio correcto
    Given un estudiante con notas 4.0, 3.5 y 4.5
    When se calcula el promedio
    Then el promedio debe ser 4.0

  Scenario: Verificar estado reprobado
    Given un estudiante con notas 2.0, 2.5 y 2.4
    When se calcula el promedio
    Then el estado del estudiante debe ser "REPROBADO"

  Scenario: Verificar estado aprobado
    Given un estudiante con notas 3.5, 4.0 y 3.8
    When se calcula el promedio
    Then el estado del estudiante debe ser "APROBADO"