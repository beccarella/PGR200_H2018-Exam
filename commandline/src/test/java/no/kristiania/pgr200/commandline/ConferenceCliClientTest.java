package no.kristiania.pgr200.commandline;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConferenceCliClientTest {
    private ConferenceCommandlineClient client = new ConferenceCommandlineClient();

    @Test
    public void shouldDecodeAddCommand() {
        String title = SampleData.sampleText(5);
        String description = SampleData.sampleText(10);
        ConferenceClientCommand command = client.decodeCommand(new String[] {
                "add",
                "-title", title,
                "-description", description
        });
        AddTalkCommand expectedCommand = new AddTalkCommand();
        expectedCommand.setTitle(title);
        expectedCommand.setDescription(description);
        assertThat(command).isInstanceOf(AddTalkCommand.class)
                .isEqualToComparingFieldByField(expectedCommand);
    }

    @Test
    public void shouldDecodeAddCommandWithTopic() {
        String title = SampleData.sampleText(5);
        String description = SampleData.sampleText(10);
        String topic = SampleData.sampleTopic();
        ConferenceClientCommand command = client.decodeCommand(new String[] {
                "add",
                "-title", title,
                "-topic", topic,
                "-description", description
        });
        AddTalkCommand expectedCommand = new AddTalkCommand();
        expectedCommand.setTitle(title);
        expectedCommand.setTopic(topic);
        expectedCommand.setDescription(description);
        assertThat(command).isInstanceOf(AddTalkCommand.class)
                .isEqualToComparingFieldByField(expectedCommand);
    }

    @Test
    public void shouldDecodeListCommandWithTopic() {
        String title = SampleData.sampleText(5);
        String description = SampleData.sampleText(10);
        String topic = SampleData.sampleTopic();
        ConferenceClientCommand command = client.decodeCommand(new String[] {
                "list",
                "-topic", topic,
        });
        ListTalkCommand expectedCommand = new ListTalkCommand();
        expectedCommand.setTopic(topic);
        assertThat(command).isInstanceOf(ListTalkCommand.class)
                .isEqualToComparingFieldByField(expectedCommand);
    }
}
