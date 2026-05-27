
@2E2
Feature: Gestion de Estudiantes
  Scenario: Registro exitoso de un nuevo estudiante
    Given que el usuario esta en la pagina de inicio
    When el usuario hace click en la opcion del menu "Registrar Estudiante"
    And ingresa el nombre "Juan Carlos" y el programa "Ingenieria de Sistemas"
    And hace click en el boton "Registrar"
    Then el sistema debe mostrar un mensaje de confirmacion "¡Estudiante registrado con éxito!"