package no.kristiania.pgr200.commandline.commands;

import no.kristiania.pgr200.http.client.HttpPostRequest;

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

    public HttpPostRequest createRequest(String host, int port) {
        HttpPostRequest request =  new HttpPostRequest(host, port, "/api/talks");
        request.getFormQuery().add("title", getTitle());
        request.getFormQuery().add("description", getDescription());
        request.getFormQuery().add("topic", getTopic());

        return request;
    }

    @Override
    public void readArguments(String[] args) {
        setTitle(getArgument(args, "-title"));
        setTopic(getArgument(args, "-topic"));
        setDescription(getArgument(args, "-description"));
    }
}