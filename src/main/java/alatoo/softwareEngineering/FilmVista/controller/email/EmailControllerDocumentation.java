package alatoo.softwareEngineering.FilmVista.controller.email;

import alatoo.softwareEngineering.FilmVista.model.dto.email.RecoveryRequest;
import alatoo.softwareEngineering.FilmVista.model.dto.email.VerifyRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/email")
@Tag(name = "Email API", description = "This controller is responsible for email verification , recovery password ")
public sealed interface EmailControllerDocumentation permits EmailController {
    @PostMapping("/verify")
    @Operation(summary = "Verify email" , description = "Send an OTP to the user's email for password reset verification.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email sent for verification successful"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    ResponseEntity<String> verifyEmail(@RequestBody VerifyRequest request);
    @PostMapping("/recoveryPassword")
    @Operation(summary = "Recovery password" , description = "Send an OTP to the user's email for password reset verification.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email sent for verification successful"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    ResponseEntity<String> recoveryPassword(@RequestParam String code ,@RequestBody RecoveryRequest request);

    @GetMapping("/verify/{token}")
    @Operation(summary = "Verify email" , description = "Send an OTP to the user's email for password reset verification.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email sent for verification successful"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    ResponseEntity<String> verifyEmail(@PathVariable("token")String token);


}
