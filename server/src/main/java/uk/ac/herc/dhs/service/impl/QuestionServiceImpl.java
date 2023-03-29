package uk.ac.herc.dhs.service.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herc.dhs.domain.AnswerItem;
import uk.ac.herc.dhs.domain.AnswerPage;
import uk.ac.herc.dhs.domain.Identity;
import uk.ac.herc.dhs.domain.QuestionItem;
import uk.ac.herc.dhs.domain.QuestionPage;
import uk.ac.herc.dhs.repository.QuestionPageRepository;
import uk.ac.herc.dhs.service.AnswerService;
import uk.ac.herc.dhs.service.IdentityService;
import uk.ac.herc.dhs.service.QuestionService;
import uk.ac.herc.dhs.service.dto.QuestionPageDTO;
import uk.ac.herc.dhs.service.mapper.QuestionPageMapper;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final IdentityService identityService;
    private final AnswerService answerService;
    private final QuestionPageMapper questionPageMapper;
    private final QuestionPageRepository questionPageRepository;

    public QuestionServiceImpl(
        IdentityService identityService,
        AnswerService answerService,
        QuestionPageMapper questionPageMapper,
        QuestionPageRepository questionPageRepository
    ) {
        this.identityService = identityService;
        this.answerService = answerService;
        this.questionPageMapper = questionPageMapper;
        this.questionPageRepository = questionPageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionPageDTO getCurrentPage(String responseId) {

        Set<QuestionPage> questionPages = questionPageRepository.findByActiveTrue();

        Identity identity = identityService.getIdentity(responseId);
        questionPages = removeNonShowPages(questionPages, identity);

        if (identity.getChoseExit()) {
            return null;
        }

        Set<AnswerPage> answerPages = identity.getAnswerPages();
        questionPages = removeSkippedPages(questionPages, answerPages);
        questionPages = removeAnsweredPages(questionPages, answerPages);

        Optional<QuestionPage> currentPageOptional = getNextPage(questionPages);

        if (currentPageOptional.isPresent()) {
            return questionPageMapper.toDto(currentPageOptional.get());
        } else {
            return null;
        }
    }

    private Set<QuestionPage> removeNonShowPages(Set<QuestionPage> questionPages, Identity identity) {
        Map<QuestionItem, AnswerItem> answerItemMap = answerService.getAnswerItemsMappedByQuestionItem(identity);
        Set<QuestionPage> removePages = new HashSet<QuestionPage>();

        for (QuestionPage questionPage : questionPages) {
            QuestionItem showItem = questionPage.getShowItem();
            if (showItem != null) {
                AnswerItem showAnswerItem = answerItemMap.get(showItem);
                if (showAnswerItem != null) {
                    Boolean show = showAnswerItem.getSelected();
                    if (!show) {
                        removePages.add(questionPage);
                    }
                }
            }
        }
        questionPages.removeAll(removePages);
        return questionPages;
    }

    private Set<QuestionPage> removeSkippedPages(Set<QuestionPage> questionPages, Set<AnswerPage> answerPages) {
        for (AnswerPage answerPage : answerPages) {
            if (answerPage.getSkipChosen()) {

                QuestionPage skipPage = answerPage.getQuestionPage();
                Set<QuestionPage> skippedPages = new HashSet<QuestionPage>();

                for (QuestionPage questionPage : questionPages) {
                    if (
                        (questionPage.getSkipPage() != null) &&
                        questionPage.getSkipPage().equals(skipPage)
                    ) {
                        skippedPages.add(questionPage);
                    }
                }
                questionPages.removeAll(skippedPages);
            }
        }
        return questionPages;
    }

    private Set<QuestionPage> removeAnsweredPages(Set<QuestionPage> questionPages, Set<AnswerPage> answerPages) {
        for (AnswerPage answerPage : answerPages) {
            questionPages.remove(answerPage.getQuestionPage());
        }
        return questionPages;
    }

    private Optional<QuestionPage> getNextPage(Set<QuestionPage> questionPages) {
        return questionPages.stream()
                .reduce((next, questionPage) -> questionPage.getOrder() < next.getOrder() ? questionPage : next);
    }
}
