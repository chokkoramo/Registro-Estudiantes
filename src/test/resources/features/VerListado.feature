@2E2
  Feature: Ver listado de todos los estudiantes
    Scenario Outline: Registro de estudiantes y verificacion del listado
      Given que el usuario esta en la pagina de inicio
      When el usuario hace click en la opcion del menu "Registrar Estudiante"
      And ingresa el nombre "<nombre>" y el programa "<programa>"
      And hace click en el boton "Registrar"
      And el usuario hace click en la opcion del menu "Ver Listado"
      Then el sistema debe mostrar a "<nombre>" en la lista de estudiantes

      Examples:
        | nombre      | programa               |
        | Juan Carlos | Ingenieria de Sistemas |
        | Ana Maria   | Medicina               |
        | Pedro Gomez | Administracion         |


