package com.toolbox.toolbox.stepdefinitions;

import com.toolbox.toolbox.tasks.ConnectTo;
import com.toolbox.toolbox.tasks.ValidateCredentials;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;

public class AuthStepDefinition {
    Actor usuario = Actor.named("usuario");

    @Before
    public void config(){

        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
    }

    @Given("el usuario esta en la pagina de login")
    public void dadoQueElUsuarioIngresaUnParValidos() {
        usuario.attemptsTo(ConnectTo.theService());
    }

    @When("el usuario ingresa un  usuario y contrasena {string} y {string}")
    public void elUsuarioIngresaUnUsuarioYContrasenaY(String arg0, String arg1) {
        usuario.attemptsTo(ValidateCredentials.withCredentials(arg0,arg1));
    }

    @Then("el sistema responde con un codigo de aceptacion y su token")
    public void elSistemaRespondeConUnCodigoDeAceptacionYSuToken() {
        usuario.should(seeThatResponse(response -> response.statusCode(200)
                .body("accessToken", containsString("eyJhbGciOiJIUzUxMiJ9"))
                .body("tokenType", containsString("Bearer "))
        ));

    }


    @Then("el sistema responde con un codigo de rechazo por inhautorizacion")
    public void elSistemaRespondeConUnCodigoDeRechazoPorInhautorizacion() {
        usuario.should(seeThatResponse(response -> response.statusCode(401)));
    }
}
