package no.kristiania.pgr200.commandline;

public class ConferenceCommandlineClient {
    public ConferenceClientCommand decodeCommand(String[] args) {
        ConferenceClientCommand command;
        if (args[0].equals("list")) {
            command = new ListTalkCommand();
        } else {
            command = new AddTalkCommand();
        }


        command.readArguments(args);
        return command;
    }
}
