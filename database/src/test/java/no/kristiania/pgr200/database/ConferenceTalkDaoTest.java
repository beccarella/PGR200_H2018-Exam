package no.kristiania.pgr200.database;

import org.assertj.core.api.Assertions;
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

    }

    @Test
    public void shouldIncludeSavedTalkInListAll() throws SQLException {
        ConferenceTalk talk = sampleTalk();
        dao.save(talk);
        assertThat(dao.listAll())
                .contains(talk);
    }

    private ConferenceTalk sampleTalk() {
        ConferenceTalk talk = new ConferenceTalk();
        talk.setTitle("My Talk Title");
        talk.setDescription("A longer description of the talk");
        return talk;
    }
}
