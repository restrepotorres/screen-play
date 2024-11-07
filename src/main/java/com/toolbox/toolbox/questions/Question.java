package com.toolbox.toolbox.questions;

import net.serenitybdd.screenplay.Actor;

public class Question implements net.serenitybdd.screenplay.Question<Boolean> {

    private Question(){}

    @Override
    public Boolean answeredBy(Actor actor) {

        return false;
    }

    public static Question validacion() {
        return new Question();
    }
}