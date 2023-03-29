package uk.ac.herc.dhs.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import uk.ac.herc.dhs.web.rest.TestUtil;

class QuestionPageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionPage.class);
        QuestionPage questionPage1 = new QuestionPage();
        questionPage1.setId(1L);
        QuestionPage questionPage2 = new QuestionPage();
        questionPage2.setId(questionPage1.getId());
        assertThat(questionPage1).isEqualTo(questionPage2);
        questionPage2.setId(2L);
        assertThat(questionPage1).isNotEqualTo(questionPage2);
        questionPage1.setId(null);
        assertThat(questionPage1).isNotEqualTo(questionPage2);
    }
}
