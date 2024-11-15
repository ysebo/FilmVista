package alatoo.softwareEngineering.FilmVista.controller.email;

import alatoo.softwareEngineering.FilmVista.model.dto.email.RecoveryRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.email.VerifyRequest;
import alatoo.softwareEngineering.FilmVista.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level= lombok.AccessLevel.PRIVATE, makeFinal = true)
public final class EmailController implements EmailControllerDocumentation{
    EmailService emailService;

    @Override
    public ResponseEntity<String> verifyEmail(VerifyRequest request) {
        emailService.verify(request);
        return ResponseEntity.ok("Your email is linked successfully");
    }

    @Override
    public ResponseEntity<String> recoveryPassword(String code , RecoveryRequest request) {
        emailService.recovery(code ,request);
        return ResponseEntity.ok("Code was sent to your email");
    }

    @Override
    public ResponseEntity<String> verifyEmail(String token) {
        if (emailService.validateVerificationToken(token)) {
            return new ResponseEntity<>("Email verified successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid or expired token", HttpStatus.BAD_REQUEST);
        }
    }
}
