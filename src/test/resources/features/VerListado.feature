@2E2
  Feature: Ver listado de todos los estudiantes
    Scenario Outline: Registro de estudiantes y verificacion del listado
      Given el usuario da click en la opcion del menu Registrar Estudiante
      And ingresa su nombre "<nombre>" y el programa "<programa>"
      And hace click al boton registrar
      And el usuario hace click en la opcion del menu ver listado
      Then el sistema debe mostrar a "<nombre>" en la lista de estudiantes

      Examples:
        | nombre      | programa               |
        | Juan Carlos | Ingenieria de Sistemas |
        | Ana Maria   | Medicina               |
        | Pedro Gomez | Administracion         |


