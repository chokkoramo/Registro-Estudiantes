@Promedio
Feature: Consultar promedio de los estudiantes

  Scenario: Consultar el promedio de un estudiante registrado por su ID
    Given el usuario esta en la pagina de inicio
    When el usuario hace click en la opcion del menu "GestionarEstudiantes"
    And ingresa el ID "1" en el campo de busqueda
    And hace click en el boton "Ver Promedio"
    Then el sistema debe mostrar el resultado "Promedio: 4.5"