package com.toolbox.toolbox.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class ConsumeSubject implements Task {
    private final String subjectId;

    public ConsumeSubject(String subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String getof = "subjectfull/"+subjectId;
        //en header(###AQUI MANDAR LA PARTE DE AUTORIZARTI###)
        //formParam() para pasar parametros
        actor.attemptsTo(
                Get.resource(getof).with(
                        request -> request.relaxedHTTPSValidation()
                                .formParam("Grant_type","Typer_value")
                )
        );
    }

    public static ConsumeSubject service(String subjectId) {
        return Tasks.instrumented(ConsumeSubject.class,subjectId);
    }
}
