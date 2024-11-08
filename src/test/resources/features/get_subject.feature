Feature: Consultar materias
  Como usuario del portal, quiero consultar la información
  relacionada con las materias de la carrera de ingeniería de sistemas
  para obtener detalles específicos de cada materia.

  Scenario: El usuario busca la informacion de todas las materias
    Given dado que el usuario esta conectado la plataforma toolbox
    When el usuario busca materias sin ningun filtro
    Then el sistema responde con una lista con la informacion de todas las materias

  Scenario:  El usuario busca una materia existente por su codigo
    Given dado que el usuario esta conectado la plataforma toolbox
    When el usuario ingresa el codigo de la materia "2536101"
    Then el sistema responde con la informacion detallada de la materia

  Scenario: El usuario busca una materia inexistente por código
    Given dado que el usuario esta conectado la plataforma toolbox
    When el usuario ingresa el codigo de la materia "IS999"
    Then el sistema responde informando que no existe la materia