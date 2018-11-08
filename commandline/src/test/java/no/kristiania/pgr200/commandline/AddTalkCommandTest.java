package no.kristiania.pgr200.commandline;

import no.kristiania.pgr200.commandline.commands.AddTalkCommand;
import no.kristiania.pgr200.http.client.HttpPostRequest;
import no.kristiania.pgr200.http.server.HttpQuery;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddTalkCommandTest {

//    @Test
//    public void shouldReadMandatoryArguments() {
//        AddTalkCommand expectedCommand = new AddTalkCommand();
//        expectedCommand.setTitle("My talk");
//        expectedCommand.setDescription("My description");
//        expectedCommand.setTopic("My topic");
//
//        Program program = new Program(new String[] {
//           "add", "-title", "My talk", "-description", "My description"
//        });
//        assertThat(expectedCommand).isEqualToComparingFieldByField(program.parseCommand());
//    }

    @Test
    public void shouldGenerateHttpRequest() {
        AddTalkCommand command = new AddTalkCommand();
        command.setTitle("My talk");
        command.setDescription("My description");
        command.setTopic("My topic");

        HttpPostRequest request = command.createRequest("localhost", 10080);
        assertThat(request.getUrl()).isEqualTo("http://localhost:10080/api/talks");
        HttpQuery query = new HttpQuery().add("title", "My talk")
                .add("description", "My description")
                .add("topic", "My topic");
        assertThat(request.getFormQuery()).isEqualTo(query);
    }
}
