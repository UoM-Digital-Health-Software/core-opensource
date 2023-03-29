package uk.ac.herc.dhs.service.impl;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herc.dhs.domain.AnswerItem;
import uk.ac.herc.dhs.domain.AnswerPage;
import uk.ac.herc.dhs.domain.Identity;
import uk.ac.herc.dhs.domain.QuestionItem;
import uk.ac.herc.dhs.repository.AnswerItemRepository;
import uk.ac.herc.dhs.repository.AnswerPageRepository;
import uk.ac.herc.dhs.service.AnswerService;
import uk.ac.herc.dhs.service.IdentityService;
import uk.ac.herc.dhs.service.dto.AnswerPageDTO;
import uk.ac.herc.dhs.service.mapper.AnswerPageMapper;

/**
 * Service Implementation for managing {@link AnswerSet}.
 */
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    private final AnswerPageRepository answerPageRepository;
    private final AnswerItemRepository answerItemRepository;
    private final AnswerPageMapper answerPageMapper;
    private final IdentityService identityService;

    @Value("${software.version}")
    private String softwareVersion;

    public AnswerServiceImpl(
        AnswerPageRepository answerPageRepository,
        AnswerItemRepository answerItemRepository,
        AnswerPageMapper answerPageMapper,
        IdentityService identityService
    ) {
        this.answerPageRepository = answerPageRepository;
        this.answerItemRepository = answerItemRepository;
        this.answerPageMapper = answerPageMapper;
        this.identityService = identityService;
    }

    @Override
    public AnswerPageDTO savePage(AnswerPageDTO answerPageDTO) {

        String responseId = answerPageDTO.getResponseId();
        Identity identity = identityService.getIdentity(responseId);

        AnswerPage answerPage = answerPageMapper.toEntity(answerPageDTO);
        answerPage.setIdentity(identity);
        answerPage.setSubmitted(Instant.now());
        answerPage.setSoftwareVersion(softwareVersion);
        answerPageRepository.save(answerPage);

        return answerPageDTO;
    }

    public Map<QuestionItem, AnswerItem> getAnswerItemsMappedByQuestionItem(Identity identity) {
        Set<AnswerItem> answerItems = answerItemRepository.findByAnswerAnswerPageIdentity(identity);

        Map<QuestionItem, AnswerItem> itemMap = new HashMap<QuestionItem, AnswerItem>();

        for (AnswerItem answerItem: answerItems) {
            itemMap.put(answerItem.getQuestionItem(), answerItem);
        }

        return itemMap;
    }
}
