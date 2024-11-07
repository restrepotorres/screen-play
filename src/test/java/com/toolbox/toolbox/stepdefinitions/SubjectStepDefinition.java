package com.toolbox.toolbox.stepdefinitions;

import com.toolbox.toolbox.tasks.ConnectTo;
import com.toolbox.toolbox.tasks.ConsumeAllSubjects;
import com.toolbox.toolbox.tasks.ConsumeSubject;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;
import io.restassured.RestAssured;
import java.nio.charset.StandardCharsets;
import static io.restassured.config.DecoderConfig.decoderConfig;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class SubjectStepDefinition {
    Actor usuario = Actor.named("usuario");

    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
        RestAssured.config = RestAssured.config()
                .encoderConfig(encoderConfig().defaultContentCharset("UTF-8"))
                .decoderConfig(decoderConfig().defaultContentCharset("UTF-8"));
    }

    @Given("dado que el usuario esta conectado la plataforma toolbox")
    public void estoyEnPlataforma() {
        usuario.attemptsTo(ConnectTo.theService());
    }



    @When("el usuario ingresa el codigo de la materia {string}")
    public void buscoUnaMateria(String arg0) {
        usuario.attemptsTo(ConsumeSubject.service(arg0));

    }

    @Then("el sistema responde con la informacion detallada de la materia")
    public void subjectInfo() {
        String valorEsperado = new String("Descubriendo la Física".getBytes(), StandardCharsets.UTF_8);

        usuario.should(seeThatResponse(response -> response.statusCode(200).
                body("name  ", Matchers.equalTo(valorEsperado))
                ));
    }


    @Then("el sistema responde informando que no existe la materia")
    public void noExisteResponse() {
        usuario.should(seeThatResponse(response -> response.statusCode(404)));
    }

    @When("el usuario busca materias sin ningun filtro")
    public void obtenerTodasLasMaterias() {
        usuario.attemptsTo(ConsumeAllSubjects.service());

    }

    @Then("el sistema responde con una lista con la informacion de todas las materias")
    public void allSubjectsInfo() {
        usuario.should(seeThatResponse(response -> response.statusCode(200)
             .body("[0].id", Matchers.equalTo("2508120"))
        ));
    }
}
