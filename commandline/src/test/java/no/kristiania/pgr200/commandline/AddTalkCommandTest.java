package no.kristiania.pgr200.commandline;

import no.kristiania.pgr200.commandline.commands.AddTalkCommand;
import org.junit.Test;

public class AddTalkCommandTest {

    @Test
    public void shouldReadMandatoryArgument() {
        AddTalkCommand expectedCommand = new AddTalkCommand();
        expectedCommand.setTitle("My talk");
        expectedCommand.setDescription("My description");
        expectedCommand.setTopic("My topic");

    }
}
