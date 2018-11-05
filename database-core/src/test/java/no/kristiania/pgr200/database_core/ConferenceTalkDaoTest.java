package no.kristiania.pgr200.database_core;

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
    }

    @Test
    public void shouldDeleteTalk() throws SQLException {
        ConferenceTalk talk = sampleTalk();
        dao.save(talk);
        assertThat(dao.listAll()).contains(talk);
        dao.delete(talk.getId());
        assertThat(dao.listAll())
                .doesNotContain(talk);
    }

//    @Test
//    public void shouldUpdateTalk() throws SQLException {
//        ConferenceTalk talk = sampleTalk();
//        dao.save(talk);
//        dao.update(talk.getId());
//        talk.setTitle("A new title");
//        System.out.println(talk);
//
//    }

    private ConferenceTalk sampleTalk() {
        ConferenceTalk talk = new ConferenceTalk();
        talk.setId(Long.valueOf(1));
        talk.setTitle("My Talk Title");
        talk.setDescription("A longer description of the talk");
        return talk;
    }
}
