package uk.ac.herc.dhs.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herc.dhs.domain.Answer;
import uk.ac.herc.dhs.domain.AnswerItem;
import uk.ac.herc.dhs.domain.AnswerPage;
import uk.ac.herc.dhs.domain.Identity;
import uk.ac.herc.dhs.pdf.ConsentPdf;
import uk.ac.herc.dhs.service.ConsentPdfService;
import uk.ac.herc.dhs.service.IdentityService;

@Service
@Transactional
public class ConsentPdfServiceImpl implements ConsentPdfService {

    private static final String CONSENT_PAGE_NAME = "PAGE_QUESTIONNAIRE_CONSENT";
    private static final String DOWNLOAD_PDF_CONSENT_QUESTION_NAME = "QUESTION_CONSENT_3";

    private final IdentityService identityService;

    public ConsentPdfServiceImpl(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Override
    public byte[] generate(String responseId) throws Exception {

        List<Answer> consentAnswers = getConsentAnswers(responseId);

        if (consentAnswers == null) {
            throw new Exception("Consent answers not found");
        }

        List<String> consentedStatements = getConsentedStatements(consentAnswers);

        ConsentPdf consentPdf = new ConsentPdf();
        return consentPdf.generate(consentedStatements);
    }

    private List<Answer> getConsentAnswers(String responseId) {
        Identity identity = identityService.getIdentity(responseId);
        for (AnswerPage answerPage : identity.getAnswerPages()) {
            if (answerPage.getQuestionPage().getName().equals(CONSENT_PAGE_NAME)) {
                return getSortedAnswers(answerPage);
            }
        }
        return null;
    }

    private List<Answer> getSortedAnswers(AnswerPage answerPage) {
        List<Answer> answers = new ArrayList<Answer>(answerPage.getAnswers());
        Collections.sort(answers, (answer1, answer2) -> {
            return answer1.getQuestion().getOrder() - answer2.getQuestion().getOrder();
        });
        return answers;
    }

    private List<String> getConsentedStatements(List<Answer> consentAnswers) {
        List<String> statements = new ArrayList<String>();

        for (Answer answer : consentAnswers) {
            if (!answer.getQuestion().getName().equals(DOWNLOAD_PDF_CONSENT_QUESTION_NAME)) {
                List<AnswerItem> answerItems = getSortedAnswerItems(answer);
                addConsentStatements(statements, answerItems);
            }
        }
        return statements;
    }

    private List<AnswerItem> getSortedAnswerItems(Answer answer) {
        List<AnswerItem> answerItems = new ArrayList<AnswerItem>(answer.getAnswerItems());
        Collections.sort(answerItems, (item1, item2) -> {
            return item1.getQuestionItem().getOrder() - item2.getQuestionItem().getOrder();
        });
        return answerItems;
    }

    private void addConsentStatements(List<String> statements, List<AnswerItem> answerItems) {
        for (AnswerItem answerItem : answerItems) {
            if (answerItem.getSelected()) {
                statements.add(
                        answerItem.getQuestionItem().getLabel());
            }
        }
    }
}
