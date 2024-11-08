package com.toolbox.toolbox.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.*;
public class CreateSubject implements Task {
    private String jwtToken;

    private final String requestBody = """
        {
          "id": "testid",
          "name": "test name",
          "prerequisites": ["string"],
          "corequisites": ["string"],
          "credits": 0,
          "level": 0,
          "summary": "string",
          "hoursWeek": 0,
          "usefulResources": {
            "additionalProp1": ["string"],
            "additionalProp2": ["string"],
            "additionalProp3": ["string"]
          },
          "tips": ["string"],
          "area": "string",
          "electiva": true,
          "state": true,
          "version": 0
        }
        """;

    public CreateSubject(String token) {
        this.jwtToken = token;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        // Realizar la solicitud POST con el cuerpo de la solicitud
        actor.attemptsTo(
                Post.to("/toolbox/api/v1/subjectfull").with(request -> request
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                )
        );

        // Validar que la respuesta tiene un código de estado 201 y contiene el id y nombre especificados
        actor.should(
                seeThatResponse("El sistema debería confirmar la creación con un estado HTTP 201",
                        response -> response.statusCode(201)
                                .body("id", equalTo("testid"))
                                .body("name", equalTo("test name"))
                )
        );
    }

    // Método estático para instanciar el Task
    public static CreateSubject withDetails() {
        return Tasks.instrumented(CreateSubject.class);
    }
}
