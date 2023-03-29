package uk.ac.herc.dhs.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import uk.ac.herc.dhs.web.rest.TestUtil;

class AnswerItemTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerItem.class);
        AnswerItem answerItem1 = new AnswerItem();
        answerItem1.setId(1L);
        AnswerItem answerItem2 = new AnswerItem();
        answerItem2.setId(answerItem1.getId());
        assertThat(answerItem1).isEqualTo(answerItem2);
        answerItem2.setId(2L);
        assertThat(answerItem1).isNotEqualTo(answerItem2);
        answerItem1.setId(null);
        assertThat(answerItem1).isNotEqualTo(answerItem2);
    }
}
