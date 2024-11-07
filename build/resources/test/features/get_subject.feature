# language: en
Feature: Consultar una materia
  como usuario del portal quiero consultar la información
  relacionada a las materias de la carrera de ingenieria de sistemas

  Scenario:  El usuario busca una materia por código
    Given dado que estoy conectado la plataforma toolbox
    When y selecciono buscar una materia en particular
    Then el sistema responde un body con la informacion relacionada a esa materia