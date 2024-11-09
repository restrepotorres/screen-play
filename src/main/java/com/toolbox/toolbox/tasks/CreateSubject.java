package com.toolbox.toolbox.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class CreateSubject implements Task {
    private final String jwtToken;

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

    public CreateSubject(String jwtToken) {
        this.jwtToken = jwtToken;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        // Realizar la solicitud POST con el cuerpo de la solicitud
        actor.attemptsTo(
                Post.to("subjectfull/").with(request -> request
                        .header("Authorization", "Bearer " + jwtToken)
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                )
        );
    }

    public static CreateSubject withDetails( String jwtToken) {
        return Tasks.instrumented(CreateSubject.class, jwtToken);
    }
}
