package no.kristiania.pgr200.commandline.commands;

public class AddTalkCommand implements ConferenceClientCommand {

    private String title;
    private String description;
    private String topic;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }



    @Override
    public void readArguments(String[] args) {
        setTitle(getArgument(args, "-title"));
        setTopic(getArgument(args, "-topic"));
        setDescription(getArgument(args, "-description"));
    }
}