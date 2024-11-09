package com.toolbox.toolbox.stepdefinitions;

import com.toolbox.toolbox.tasks.DeleteSubject;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class DeleteStepDefinition {
    Actor usuario = Actor.named("usuario");
    private final EnvironmentVariables environmentVariables= SystemEnvironmentVariables.createEnvironmentVariables();
    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
        usuario.can(CallAnApi.at(environmentVariables.getProperty("webdriver.base.url")));
    }


    @When("el usuario la solicitud para eliminar la materias con id {string}")
    public void elUsuarioLaSolicitudParaEliminarLaMateriasConId(String arg0) {
        String jwtToken = LastResponse.received().answeredBy(usuario).path("accessToken").toString();
        System.out.println(jwtToken);
        usuario.attemptsTo(DeleteSubject.withId(jwtToken, arg0));

    }

    @Then("el sistema deberia confirmar la eliminacion con un estado HTTP {int}")
    public void elSistemaDeberiaConfirmarLaEliminacionConUnEstadoHTTP(int arg0) {
        usuario.should(seeThatResponse(response -> response.statusCode(arg0)));
    }
}
