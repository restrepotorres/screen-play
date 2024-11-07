package com.toolbox.toolbox.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class ConsumerTheSubject implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        String getof = "subjectfull/2536101";
        //en header(###AQUI MANDAR LA PARTE DE AUTORIZARTI###)
        //formParam() para pasar parametros
        actor.attemptsTo(
                Get.resource(getof).with(
                        request -> request.relaxedHTTPSValidation()
                                .header("Accept", "application/json; charset=UTF-8")
                                .header("Content-Type", "application/json; charset=UTF-8").
                                formParam("Grant_type","Typer_value")
                )
        );
    }

    public static ConsumerTheSubject service() {
        return Tasks.instrumented(ConsumerTheSubject.class);
    }
}
