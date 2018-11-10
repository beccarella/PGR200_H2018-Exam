package no.kristiania.pgr200.commandline.commands;

public class UpdateTalkCommand implements ConferenceClientCommand {

    private String title;
    private String description;
    private String topic;

    public UpdateTalkCommand withTitle(String title) {
        this.title = title;
        return this;
    }

    public UpdateTalkCommand withDescription(String description) {
        this.description = description;
        return this;
    }

    public UpdateTalkCommand withTopic(String topic) {
        this.topic = topic;
        return this;
    }

    @Override
    public void readArguments(String[] args) {

    }
}
