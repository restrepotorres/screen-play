Feature: Crear una nueva materia
  Como administrador usuario del portal, quiero registrar una nueva materia
  proporcionando los detalles de la materia para que este disponible en el sistema.

  Scenario: El usuario crea una materia estando logueado y con datos validos
    Given que el usuario esta autenticado en la plataforma Toolbox
    When el usuario envia los detalles de una nueva materia
    Then el sistema deberia confirmar la creacion con un estado HTTP 200
    And la respuesta deberia contener el id "testid" y el nombre "test name"


  Scenario: El usuario crea una materia sin estar logueado
    Given que el usuario no esta autenticado en la plataforma Toolbox
    When el usuario envia los detalles de una nueva materia
    Then el sistema deberia confirmar la creacion con un estado HTTP 401