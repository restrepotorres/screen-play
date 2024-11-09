package com.toolbox.toolbox.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/delete_subject.feature/",
        glue = "com.toolbox.toolbox.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class DeleteRunner {
}
