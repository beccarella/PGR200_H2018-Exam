package no.kristiania.pgr200.commandline.commands;

public class UpdateTalkCommand implements ConferenceClientCommand {

    private String title;
    private String description;
    private String topic;

//    public UpdateTalkCommand withTitle(String title) {
//        this.title = title;
//        return this;
//    }
//
//    public UpdateTalkCommand withDescription(String description) {
//        this.description = description;
//        return this;
//    }
//
//    public UpdateTalkCommand withTopic(String topic) {
//        this.topic = topic;
//        return this;
//    }

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
