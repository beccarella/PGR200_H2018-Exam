package no.kristiania.pgr200.commandline.commands;

public class ShowTalkCommand implements ConferenceClientCommand {

    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void readArguments(String[] args) {
        setTitle(getArgument(args, "-title"));
    }
}
