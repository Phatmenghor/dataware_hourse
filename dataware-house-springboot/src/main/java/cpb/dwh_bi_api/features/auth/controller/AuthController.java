package cpb.dwh_bi_api.features.auth.controller;

import cpb.dwh_bi_api.features.auth.dto.LoginRequest;
import cpb.dwh_bi_api.features.auth.dto.RefreshTokenRequest;
import cpb.dwh_bi_api.features.auth.dto.TokenResponse;
import cpb.dwh_bi_api.features.auth.service.AuthService;
import cpb.dwh_bi_api.shared.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication", description = "User authentication endpoints")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user and generate JWT tokens")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Login attempt for user: {}", loginRequest.getUsername());
        try {
            TokenResponse tokenResponse = authService.login(loginRequest);
            return ResponseEntity.ok(ApiResponse.success("Login successful", tokenResponse));
        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "Refresh access token", description = "Generate a new access token using refresh token")
    public ResponseEntity<ApiResponse<TokenResponse>> refreshToken(
            @Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        log.info("Token refresh attempt");
        try {
            TokenResponse tokenResponse = authService.refreshToken(refreshTokenRequest);
            return ResponseEntity.ok(ApiResponse.success("Token refreshed successfully", tokenResponse));
        } catch (Exception e) {
            log.error("Token refresh failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/logout")
    @Operation(summary = "User logout", description = "Logout user and blacklist current token")
    public ResponseEntity<ApiResponse<Object>> logout(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        log.info("Logout attempt");
        try {
            if (authHeader == null || authHeader.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("Authorization header is required"));
            }

            authService.logout(authHeader);
            return ResponseEntity.ok(ApiResponse.success("Logout successful", null));
        } catch (Exception e) {
            log.error("Logout failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
