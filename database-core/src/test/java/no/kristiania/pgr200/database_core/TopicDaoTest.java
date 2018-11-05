package no.kristiania.pgr200.database_core;

import org.junit.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class TopicDaoTest {
    private TopicDao dao = new TopicDao(TestDataSource.createTestDataSource());

    @Test
    public void shouldInsertTopic() throws SQLException {
        Topic topic = sampleTopic();
        dao.save(topic);
        assertThat(dao.listAll()).contains(topic);
        dao.delete(topic.getId());
    }

    @Test
    public void shouldFindTalkById() throws SQLException {
        Topic topic = sampleTopic();
        dao.save(topic);
        assertThat(topic.getId()).isNotNull();
        assertThat(dao.retrieve(topic.getId())).isEqualToComparingFieldByField(topic);
    }

    @Test
    public void shouldDeleteTalk() throws SQLException {
        Topic topic = sampleTopic();
        dao.save(topic);
        assertThat(dao.listAll()).contains(topic);
        dao.delete(topic.getId());
        assertThat(dao.listAll())
                .doesNotContain(topic);
    }

    public Topic sampleTopic() {
        Topic topic = new Topic();
        topic.setId(Long.valueOf(1));
        topic.setTopicName("Some arbitrary topic");
        return topic;
    }
}
