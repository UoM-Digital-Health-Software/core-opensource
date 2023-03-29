package uk.ac.herc.dhs.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.herc.dhs.domain.Identity;
import uk.ac.herc.dhs.repository.IdentityRepository;
import uk.ac.herc.dhs.service.IdentityService;
import uk.ac.herc.dhs.service.dto.IdentityDTO;
import uk.ac.herc.dhs.service.mapper.IdentityMapper;

@Service
@Transactional
public class IdentityServiceImpl implements IdentityService {

    private final Logger log = LoggerFactory.getLogger(IdentityServiceImpl.class);

    private final IdentityRepository identityRepository;

    private final IdentityMapper identityMapper;

    public IdentityServiceImpl(IdentityRepository identityRepository, IdentityMapper identityMapper) {
        this.identityRepository = identityRepository;
        this.identityMapper = identityMapper;
    }

    @Override
    public String create(IdentityDTO identityDTO) {
        log.debug("Request to create Identity : {}", identityDTO);

        String responseId = findResponseId(identityDTO);

        if (responseId != null) {
            return responseId;
        }

        Identity identity = generateIdentity(identityDTO);
        return identity.getResponseId();
    }

    @Override
    public Identity getIdentity(String responseId) {
        Optional<Identity> identityOptional = identityRepository.getOneByResponseId(responseId);

        if (!identityOptional.isPresent()) {
            throw new RuntimeException("Could not find Identity with Response ID: " + responseId);
        }

        return identityOptional.get();
    }

    @Override
    public void chooseExit(String responseId) {
        Identity identity = getIdentity(responseId);
        identity.setChoseExit(true);
        identityRepository.save(identity);
    }

    String findResponseId(IdentityDTO identityDTO) {
        String responseId = identityDTO.getResponseId();
        if (responseId != null) {
            Optional<Identity> identity = identityRepository.getOneByResponseId(responseId);

            if (identity.isPresent()) {
                return responseId;
            }
        }
        return null;
    }

    Identity generateIdentity(IdentityDTO identityDTO) {

        String studyId = identityDTO.getStudyId();
        String participantId = identityDTO.getParticipantId();
        String machineId = identityDTO.getMachineId();

        if ((studyId != null) && (participantId != null) && (machineId != null)) {
            Identity identity = createIdentity(identityDTO);
            return identityRepository.save(identity);
        }

        return null;
    }

    private Identity createIdentity(IdentityDTO identityDTO) {
        Identity identity = identityMapper.toEntity(identityDTO);
        String responseId = getUUID4String();
        identity.setResponseId(responseId);
        identity.setCreated(Instant.now());
        identity.setChoseExit(false);
        return identityRepository.save(identity);
    }

    private String getUUID4String() {
        return UUID.randomUUID().toString();
    }

}
