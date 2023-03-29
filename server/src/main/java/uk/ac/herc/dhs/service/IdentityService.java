package uk.ac.herc.dhs.service;

import uk.ac.herc.dhs.domain.Identity;
import uk.ac.herc.dhs.service.dto.IdentityDTO;

public interface IdentityService {
    String create(IdentityDTO identityDTO);
    Identity getIdentity(String responseId);
    void chooseExit(String responseId);
}
