package uk.ac.herc.dhs.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import uk.ac.herc.dhs.web.rest.TestUtil;

class AnswerPageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerPage.class);
        AnswerPage answerPage1 = new AnswerPage();
        answerPage1.setId(1L);
        AnswerPage answerPage2 = new AnswerPage();
        answerPage2.setId(answerPage1.getId());
        assertThat(answerPage1).isEqualTo(answerPage2);
        answerPage2.setId(2L);
        assertThat(answerPage1).isNotEqualTo(answerPage2);
        answerPage1.setId(null);
        assertThat(answerPage1).isNotEqualTo(answerPage2);
    }
}
