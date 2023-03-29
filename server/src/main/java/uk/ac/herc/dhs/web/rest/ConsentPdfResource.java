package uk.ac.herc.dhs.web.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import uk.ac.herc.dhs.service.ConsentPdfService;

@RestController
@RequestMapping("/api")
public class ConsentPdfResource {

  private final ConsentPdfService consentPdfService;

  public ConsentPdfResource(ConsentPdfService consentPdfService) {
    this.consentPdfService = consentPdfService;
  }

  @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
  public @ResponseBody byte[] generateConsentPdf(@RequestParam String responseId) throws Exception {
    return consentPdfService.generate(responseId);
  }
}
