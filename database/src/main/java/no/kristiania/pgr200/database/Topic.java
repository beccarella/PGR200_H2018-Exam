package no.kristiania.pgr200.database;

import java.util.Objects;

public class Topic {
    private long id;
    private String topicName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(topicName, topic.topicName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicName);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicName='" + topicName + '\'' +
                '}';
    }
}
