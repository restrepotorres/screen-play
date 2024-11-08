package com.toolbox.toolbox.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
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
public class PostDeleteStepDefinition {
    Actor usuario = Actor.named("usuario");
    String jwtToken;
    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
    }


    @Given("que el usuario está autenticado en la plataforma Toolbox")
    public void queElUsuarioEstaAutenticadoEnLaPlataformaToolbox() {
        usuario.attemptsTo(ValidateCredentials.withCredentials("victor","1234"));
        jwtToken = LastResponse.received().answeredBy(usuario).path("accessToken").toString();
    }

    @When("el usuario envia los detalles de una nueva materia")
    public void elUsuarioEnviaLosDetallesDeUnaNuevaMateria() {

    }
}
