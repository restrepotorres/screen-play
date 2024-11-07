package com.toolbox.toolbox.stepdefinitions;

import com.toolbox.toolbox.tasks.ConnectTo;
import com.toolbox.toolbox.tasks.ConsumerTheSubject;
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
import static org.hamcrest.Matchers.hasItems;

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

    @Given("dado que estoy conectado la plataforma toolbox")
    public void estoyEnPlataforma() {
        usuario.attemptsTo(ConnectTo.theService());
    }

    @When("y selecciono buscar una materia en particular")
    public void buscoUnaMateria() {
        usuario.attemptsTo(ConsumerTheSubject.service());
    }

    @Then("el sistema responde un body con la informacion relacionada a esa materia")
    public void elSistemaRespondeUnBodyConLaInformacionRelacionadaAEsaMateria() {
        String valorEsperado = new String("Descubriendo la Física".getBytes(), StandardCharsets.UTF_8);

        usuario.should(seeThatResponse(response -> response.statusCode(200).
                body("name  ", Matchers.equalTo(valorEsperado))
                ));
    }
    

}
