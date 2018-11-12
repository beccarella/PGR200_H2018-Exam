package no.kristiania.pgr200.database;

import no.kristiania.pgr200.database.dao.ConferenceTalkDao;
import no.kristiania.pgr200.database.entity.ConferenceTalk;
import org.junit.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


public class ConferenceTalkDaoTest {

    private ConferenceTalkDao dao = new ConferenceTalkDao(TestDataSource.createTestDataSource());

    @Test
    public void shouldInsertConferenceTalks() throws SQLException {
        ConferenceTalk talk = sampleTalk();
        dao.save(talk);
        assertThat(dao.listAll()).contains(talk);
        dao.delete(talk.getId());
    }

    @Test
    public void shouldFindTalkById() throws SQLException {
        ConferenceTalk talk = sampleTalk();
        dao.save(talk);
        assertThat(talk.getId()).isNotNull();
        assertThat(dao.retrieve(talk.getId())).isEqualToComparingFieldByField(talk);
        dao.delete(talk.getId());
    }

    @Test
    public void shouldDeleteTalk() throws SQLException {
        ConferenceTalk talk = sampleTalk();
        dao.save(talk);
        assertThat(dao.listAll()).contains(talk);
        dao.delete(talk.getId());
        assertThat(dao.listAll()).doesNotContain(talk);
    }

//    @Test
//    public void shouldUpdateTalkTitle() throws SQLException {
//        ConferenceTalk talk = sampleTalk();
//        dao.save(talk);
//        assertThat(dao.listAll()).contains(talk);
//        ConferenceTalk talkUpdate = updatedTalk();
//        assertThat(dao.listAll()).contains(talkUpdate);
//    }

    private ConferenceTalk sampleTalk() {
        ConferenceTalk talk = new ConferenceTalk();
        talk.setId(Long.valueOf(1));
        talk.setTitle("My Talk Title");
        talk.setDescription("A longer description of the talk");
        talk.setTopic("Some arbitrary topic name");
        return talk;
    }

//    private ConferenceTalk updatedTalk() throws SQLException {
//        ConferenceTalk talk = new ConferenceTalk();
//        dao.update(Long.valueOf(1), "A new Title");
//        return talk;
//    }
}
