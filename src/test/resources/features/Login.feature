@2E2 @Login
Feature: Login de usuario
  Como usuario de la aplicacion
  Quiero poder inicial sesion con credenciales validas
  Para acceder a el registro de usuarios

  Scenario: Crear cuenta e iniciar sesion
    Given el usuario abre la pagina de login
    When el usuario se registra con usuario "user_admin" y constrasena "admin_contra"
    And el usuario ingresa con "user_admin" y "admin_contra"
    Then el usuario debe ver el texto "Bienvenido al Sistema de Estudiantes" en la pantalla inicial

  Scenario: Login exitoso
    Given el usuario abre la pagina de login
    When el usuario ingresa "user_admin" y "admin_contra"
    Then el usuario debe ver el texto "Bienvenido al Sistema de Estudiantes" en la pantalla inicial