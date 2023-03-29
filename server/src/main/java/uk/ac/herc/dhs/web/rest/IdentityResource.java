package uk.ac.herc.dhs.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.herc.dhs.service.IdentityService;
import uk.ac.herc.dhs.service.dto.IdentityDTO;

/**
 * REST controller for managing {@link uk.ac.herc.dhs.domain.Identity}.
 */
@RestController
@RequestMapping("/api")
public class IdentityResource {

    private final IdentityService identityService;

    public IdentityResource(IdentityService identityService) {
        this.identityService = identityService;
    }

    @PostMapping("/identity")
    public ResponseEntity<String> createIdentity(@Valid @RequestBody IdentityDTO identityDTO)
            throws URISyntaxException {
        String responseId = identityService.create(identityDTO);
        return ResponseEntity.created(new URI("/api/identity")).body(responseId);
    }

    @PostMapping("/exit")
    public ResponseEntity<String> chooseExit(@Valid @RequestBody IdentityDTO identityDTO) throws URISyntaxException {
        String responseId = identityDTO.getResponseId();
        identityService.chooseExit(responseId);
        return new ResponseEntity<>("exit", HttpStatus.OK);
    }
}
