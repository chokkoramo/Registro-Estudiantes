@Estado
Feature: Consultar estado academico de un estudiante

  Scenario Outline: Verificar diferentes estados de estudiantes por su ID
    Given el usuario esta en la pagina de inicio
    When el usuario hace click en la opcion del menu "GestionarEstudiantes"
    And ingresa el ID "<id_estudiante>" en el campo de busqueda
    And hace click en el boton "Ver Estado"
    Then el sistema debe mostrar el resultado "Estado: <estado_esperado>"

    Examples:
      | id_estudiante | estado_esperado |
      | 1             | REPROBADO       |
      | 2             | APROBADO        |
      | 103           | undefined       |