Feature: Gestion de Estudiantes

  Scenario: Registro exitoso de un nuevo estudiante
    Given que le usuario se encuentra en la pagina de registro de estudiantes
    When ingresa el nombre "Juan Carlos" y el programa "Ingenieria de Sistemas"
    And hace click en el boton "Registrar"
    Then el sistema debe mostrar un mensaje de confirmacion "¡Estudiante registrado con éxito!"