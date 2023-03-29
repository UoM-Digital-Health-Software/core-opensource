package uk.ac.herc.dhs.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import uk.ac.herc.dhs.web.rest.TestUtil;

class QuestionItemTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionItem.class);
        QuestionItem questionItem1 = new QuestionItem();
        questionItem1.setId(1L);
        QuestionItem questionItem2 = new QuestionItem();
        questionItem2.setId(questionItem1.getId());
        assertThat(questionItem1).isEqualTo(questionItem2);
        questionItem2.setId(2L);
        assertThat(questionItem1).isNotEqualTo(questionItem2);
        questionItem1.setId(null);
        assertThat(questionItem1).isNotEqualTo(questionItem2);
    }
}
