package uk.ac.herc.dhs.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.herc.dhs.IntegrationTest;
import uk.ac.herc.dhs.domain.Identity;
import uk.ac.herc.dhs.repository.IdentityRepository;
import uk.ac.herc.dhs.service.dto.IdentityDTO;

@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class IdentityResourceIT {

    private static final String STUDY_ID = "hryws";
    private static final String PARTICIPANT_ID = "abc987";
    private static final String MACHINE_ID = "AAAAAAAAAA";

    private static final String ENTITY_API_URL = "/api/identity";

    private static final String UUID4_REGEX = "^([a-z]|[A-Z]|[0-9]|[-]){36}$";

    @Autowired
    private IdentityRepository identityRepository;

    @Autowired
    private MockMvc restIdentityMockMvc;

    @Test
    @Transactional
    void createIdentity() throws Exception {
        int databaseSizeBeforeCreate = identityRepository.findAll().size();

        IdentityDTO identityDTO = new IdentityDTO();
        identityDTO.setStudyId(STUDY_ID);
        identityDTO.setParticipantId(PARTICIPANT_ID);
        identityDTO.setMachineId(MACHINE_ID);

        // POST
        restIdentityMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(identityDTO))
            )
            .andExpect(status().isCreated());

        // Validate
        List<Identity> identityList = identityRepository.findAll();
        assertThat(identityList).hasSize(databaseSizeBeforeCreate + 1);

        Identity testIdentity = identityList.get(identityList.size() - 1);
        assertThat(testIdentity.getStudyId()).isEqualTo(STUDY_ID);
        assertThat(testIdentity.getParticipantId()).isEqualTo(PARTICIPANT_ID);
        assertThat(testIdentity.getResponseId()).matches(UUID4_REGEX);
        assertThat(testIdentity.getMachineId()).isEqualTo(MACHINE_ID);
        assertThat(testIdentity.getChoseExit()).isEqualTo(false);
    }

    @Test
    @Transactional
    void exitQuestionnaire() throws Exception {
        createIdentity();

        List<Identity> identityList = identityRepository.findAll();
        String responseId = identityRepository.findAll().get(identityList.size() - 1).getResponseId();

        IdentityDTO identityDTO = new IdentityDTO();
        identityDTO.setResponseId(responseId);

        // POST
        restIdentityMockMvc
            .perform(
                post("/api/exit")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(identityDTO))
            )
            .andExpect(status().isOk());

        // Validate
        identityList = identityRepository.findAll();

        Identity testIdentity = identityList.get(identityList.size() - 1);
        assertThat(testIdentity.getChoseExit()).isEqualTo(true);
    }
}
