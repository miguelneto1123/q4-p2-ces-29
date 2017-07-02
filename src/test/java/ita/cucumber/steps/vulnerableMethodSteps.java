package ita.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ita.VulnerableClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by miguel on 7/1/17.
 */
public class vulnerableMethodSteps {
    private String filename;
    private VulnerableClass cl = new VulnerableClass();
    private ByteArrayOutputStream baos;
    private ByteArrayInputStream bais;
    private boolean exists;

    @Given("^I have a file in \"([^\"]*)\"$")
    public void iHaveAFileIn(String arg0) throws Throwable {
        filename = arg0;
    }

    @When("^it has (no )?content$")
    public void itHasContent(String arg0) throws Throwable {
        String commands = "R\nq\n";

        InputStream stdin = System.in;
        PrintStream stdout = System.out;

        bais = new ByteArrayInputStream(commands.getBytes());
        baos = new ByteArrayOutputStream();
        System.setIn(bais);
        System.setOut(new PrintStream(baos));

        cl.vulnerableMethod(filename);
        System.setIn(stdin);
        System.setOut(stdout);
    }

    @Then("^its content should be printed$")
    public void itsContentShouldBePrinted() throws Throwable {
        String plainResult = "Digite a operacao desejada para realizar no arquivo "+
                "<R para ler um arquivo, W para escrever em um arquivo> ou Q para sair";
        assertThat(baos.toString().contains(plainResult) &&
                baos.toString().length() > plainResult.length() * 2, is(true));
    }

    @Then("^its content should not be printed$")
    public void itsContentShouldNotBePrinted() throws Throwable {
        String plainResult = "Digite a operacao desejada para realizar no arquivo "+
                "<R para ler um arquivo, W para escrever em um arquivo> ou Q para sair";
        assertThat(baos.toString().equals(plainResult+plainResult), is(true));
    }

    @When("^it does not exist and I am trying to read it$")
    public void itDoesNotExist() throws Throwable {
        String commands = "R\nq\n";

        InputStream stdin = System.in;
        PrintStream stdout = System.out;

        bais = new ByteArrayInputStream(commands.getBytes());
        baos = new ByteArrayOutputStream();
        System.setIn(bais);
        System.setOut(new PrintStream(baos));

        exists = cl.vulnerableMethod(filename);
        System.setIn(stdin);
        System.setOut(stdout);
    }

    @Then("^it should not work$")
    public void itShouldNotWork() throws Throwable {
        assertThat(exists, is(false));
    }

    @When("^I attempt to write on it$")
    public void iAttemptToWriteOnIt() throws Throwable {
        String commands = "w\r\nlululu\r\nw\r\nhahaha\r\nq\n";

        InputStream stdin = System.in;
        PrintStream stdout = System.out;

        bais = new ByteArrayInputStream(commands.getBytes());
        baos = new ByteArrayOutputStream();
        System.setIn(bais);
        System.setOut(new PrintStream(baos));

        exists = cl.vulnerableMethod(filename);
        System.setIn(stdin);
        System.setOut(stdout);
    }

    @Then("^it should work$")
    public void itShouldWork() throws Throwable {
        assertThat(exists, is(true));
    }
}
