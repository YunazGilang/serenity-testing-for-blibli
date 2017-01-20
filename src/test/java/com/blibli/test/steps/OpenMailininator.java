package com.blibli.test.steps;

import com.blibli.test.steps.actor.User;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * Created by Yunaz on 1/19/2017.
 */
public class OpenMailininator {
    @Steps
    User actor;

    @When("User want to open the mailinator page")
    public void open_the_mailinator_page(){
        actor.user_open_mailinator();
    }

    @Then("The user should see the mailinator web page")
    public void check_is_the_mailinator(){
        actor.is_the_mailinator();
    }
}