Feature: Autenticarse
  Como usuario del portal, quiero logearme
  para  realizar acciones que requiren estar autenticado.

  Scenario: El usuario se auntentica con credenciales validas
    Given el usuario esta en la pagina de login
    When el usuario ingresa un  usuario y contrasena "victor" y "1234"
    Then el sistema responde con un codigo de aceptacion y su token


  Scenario: El usuario se auntentica con credenciales NO validas
    Given el usuario esta en la pagina de login
    When el usuario ingresa un  usuario y contrasena "xxxx" y "xxxx"
    Then el sistema responde con un codigo de rechazo por inhautorizacion