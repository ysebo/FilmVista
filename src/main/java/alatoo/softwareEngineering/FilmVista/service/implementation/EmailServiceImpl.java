package alatoo.softwareEngineering.FilmVista.service.implementation;

import alatoo.softwareEngineering.FilmVista.exception.CustomException;
import alatoo.softwareEngineering.FilmVista.model.domain.User;
import alatoo.softwareEngineering.FilmVista.model.dto.email.RecoveryRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.email.VerifyRequest;
import alatoo.softwareEngineering.FilmVista.repository.UserRepository;
import alatoo.softwareEngineering.FilmVista.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void verify(VerifyRequest request) {
        Optional<User> user = userRepository.findByEmail(request.email());
        if (user.isEmpty()) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        String token = generateVerificationToken();
        String verificationUrl = "http://localhost:9089/api/email/verify/" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ecomarket1111@gmail.com");
        message.setTo(request.email());
        message.setSubject("Email Verification");
        message.setText("Please verify your email by clicking the link: " + verificationUrl);
        mailSender.send(message);
    }

    public String generateVerificationToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }

    public boolean validateVerificationToken(String token) {
        Optional<User> user = userRepository.findByVerificationToken(token);
        if (user.isEmpty()) {
            return false;
        }
        String expectedToken = user.get().getVerificationToken();
        return encoder.matches(token, expectedToken);
    }

    @Override
    public void recovery(String code, RecoveryRequest request) {
        Optional<User> user = userRepository.findByUuid(code);
        if (user.isEmpty()) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        String password1 = request.password1();
        String password2 = request.password2();
        if(!password1.equals(password2)){
            throw new CustomException("Passwords do not match", HttpStatus.BAD_REQUEST);
        }
        user.get().setPassword(encoder.encode(password1));
        userRepository.save(user.get());

    }
}
