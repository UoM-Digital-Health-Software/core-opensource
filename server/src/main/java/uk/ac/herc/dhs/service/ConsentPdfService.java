package uk.ac.herc.dhs.service;

public interface ConsentPdfService {
    byte[] generate(String responseId) throws Exception;
}
