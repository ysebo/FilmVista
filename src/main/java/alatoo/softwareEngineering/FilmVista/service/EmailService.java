package alatoo.softwareEngineering.FilmVista.service;

import alatoo.softwareEngineering.FilmVista.model.dto.email.RecoveryRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.email.VerifyRequest;

public interface EmailService {
    void verify(VerifyRequest request);
    String generateVerificationToken();
    boolean validateVerificationToken(String token );

    void recovery(String code , RecoveryRequest request);
}
