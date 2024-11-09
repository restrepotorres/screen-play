package com.toolbox.toolbox.stepdefinitions;

import com.toolbox.toolbox.tasks.CreateSubject;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import com.toolbox.toolbox.tasks.ConnectTo;
import com.toolbox.toolbox.tasks.ValidateCredentials;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;
public class PostStepDefinition {
    Actor usuario = Actor.named("usuario");
    String jwtToken;
    private final EnvironmentVariables environmentVariables= SystemEnvironmentVariables.createEnvironmentVariables();
    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
        usuario.can(CallAnApi.at(environmentVariables.getProperty("webdriver.base.url")));
    }


    @Given("que el usuario esta autenticado en la plataforma Toolbox")
    public void queElUsuarioEstaAutenticadoEnLaPlataformaToolbox() {
        usuario.attemptsTo(ValidateCredentials.withCredentials("victor","1234"));
        jwtToken = LastResponse.received().answeredBy(usuario).path("accessToken").toString();
    }

    @When("el usuario envia los detalles de una nueva materia")
    public void elUsuarioEnviaLosDetallesDeUnaNuevaMateria() {
        usuario.attemptsTo(CreateSubject.withDetails(jwtToken));
    }

    @Then("el sistema deberia confirmar la creacion con un estado HTTP {int}")
    public void elSistemaDeberiaConfirmarLaCreacionConUnEstadoHTTP(int arg0) {
        usuario.should(seeThatResponse(response -> response.statusCode(arg0)
        ));
    }

    @And("la respuesta deberia contener el id {string} y el nombre {string}")
    public void laRespuestaDeberiaContenerElIdYElNombre(String arg0, String arg1) {
        usuario.should(seeThatResponse(response -> response
                .body("name", containsString("test name"))
                .body("id", containsString("testid"))
        ));
    }

    @Given("que el usuario no esta autenticado en la plataforma Toolbox")
    public void queElUsuarioNoEstaAutenticadoEnLaPlataformaToolbox() {
        usuario.attemptsTo(ConnectTo.theService());
    }

}
