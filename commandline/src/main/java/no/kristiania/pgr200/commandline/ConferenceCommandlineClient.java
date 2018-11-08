package no.kristiania.pgr200.commandline;

import no.kristiania.pgr200.commandline.commands.AddTalkCommand;
import no.kristiania.pgr200.commandline.commands.ConferenceClientCommand;
import no.kristiania.pgr200.commandline.commands.ListTalkCommand;

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
