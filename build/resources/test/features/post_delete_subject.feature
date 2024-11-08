Feature: Crear una nueva materia
  Como usuario del portal, quiero registrar una nueva materia
  proporcionando los detalles de la materia para que esté disponible en el sistema.

  Scenario: El usuario crea una materia con datos válidos
    Given que el usuario está autenticado en la plataforma Toolbox
    When el usuario envia los detalles de una nueva materia
    Then el sistema debería confirmar la creación con un estado HTTP 200
    And la respuesta debería contener el id "testid" y el nombre "test name"