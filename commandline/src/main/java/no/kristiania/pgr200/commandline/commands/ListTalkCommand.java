package no.kristiania.pgr200.commandline.commands;

public class ListTalkCommand implements ConferenceClientCommand {

    private String topic;

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public void readArguments(String[] args) {
        setTopic(getArgument(args, "-topic"));
    }
}