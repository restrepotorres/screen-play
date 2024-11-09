Feature: Eliminar una materia
  Como usuario administrador del portal, quiero registrar una nueva materia
  proporcionando los detalles de la materia para que este disponible en el sistema.

  Scenario: El usuario elimina una materia correctamente
    Given que el usuario esta autenticado en la plataforma Toolbox
    When el usuario la solicitud para eliminar la materias con id "testid"
    Then el sistema deberia confirmar la eliminacion con un estado HTTP 204