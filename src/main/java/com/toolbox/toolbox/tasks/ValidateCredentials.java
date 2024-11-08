package com.toolbox.toolbox.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;


public class ValidateCredentials implements Task {
    private final String username;
    private final String password;

    public ValidateCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("auth/login").with(request -> request
                        .header("Content-Type", "application/json")
                        .body("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }")
                )
        );
    }

    public static ValidateCredentials withCredentials(String username, String password) {
        return Tasks.instrumented(ValidateCredentials.class, username, password);
    }
}
