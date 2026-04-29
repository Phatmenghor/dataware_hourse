package cpb.dwh_bi_api.features.auth.service.impl;

import cpb.dwh_bi_api.features.auth.dto.LoginRequest;
import cpb.dwh_bi_api.features.auth.dto.RefreshTokenRequest;
import cpb.dwh_bi_api.features.auth.dto.TokenResponse;
import cpb.dwh_bi_api.features.auth.service.AuthService;
import cpb.dwh_bi_api.features.user.models.User;
import cpb.dwh_bi_api.features.user.repository.UserRepository;
import cpb.dwh_bi_api.shared.security.jwt.JWTGenerator;
import cpb.dwh_bi_api.shared.security.jwt.TokenBlacklistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final TokenBlacklistService tokenBlacklistService;
    private final UserRepository userRepository;

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            String accessToken = jwtGenerator.generateAccessToken(authentication);
            String refreshToken = jwtGenerator.generateRefreshToken(
                    loginRequest.getUsername(),
                    "PLATFORM_USER",
                    null
            );

            User user = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            long jwtExpiration = 900000; // 15 minutes default
            long refreshExpiration = 604800000; // 7 days default

            TokenResponse response = new TokenResponse(
                    accessToken,
                    refreshToken,
                    jwtExpiration,
                    refreshExpiration
            );
            response.setUsername(user.getUsername());
            response.setStaffId(user.getStaffId());
            response.setFullName(user.getFullName());
            response.setExpiresAt(LocalDateTime.now().plusSeconds(jwtExpiration / 1000));
            response.setRefreshExpiresAt(LocalDateTime.now().plusSeconds(refreshExpiration / 1000));

            log.info("User logged in successfully: {}", loginRequest.getUsername());
            return response;

        } catch (Exception e) {
            log.error("Login failed for user: {}", loginRequest.getUsername(), e);
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
    }

    @Override
    public TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        try {
            String refreshToken = refreshTokenRequest.getRefreshToken();

            if (!StringUtils.hasText(refreshToken)) {
                throw new RuntimeException("Refresh token is required");
            }

            if (!jwtGenerator.validateToken(refreshToken)) {
                throw new RuntimeException("Invalid refresh token");
            }

            if (jwtGenerator.isTokenExpired(refreshToken)) {
                throw new RuntimeException("Refresh token is expired");
            }

            String username = jwtGenerator.getUsernameFromJWT(refreshToken);
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            List<String> roles = user.getRole() != null
                    ? List.of("ROLE_" + user.getRole().getName())
                    : List.of();

            String newAccessToken = jwtGenerator.generateAccessTokenFromUsername(username, roles);
            String newRefreshToken = jwtGenerator.generateRefreshToken(
                    username,
                    "PLATFORM_USER",
                    null
            );

            long jwtExpiration = 900000;
            long refreshExpiration = 604800000;

            TokenResponse response = new TokenResponse(
                    newAccessToken,
                    newRefreshToken,
                    jwtExpiration,
                    refreshExpiration
            );
            response.setUsername(user.getUsername());
            response.setStaffId(user.getStaffId());
            response.setFullName(user.getFullName());
            response.setExpiresAt(LocalDateTime.now().plusSeconds(jwtExpiration / 1000));
            response.setRefreshExpiresAt(LocalDateTime.now().plusSeconds(refreshExpiration / 1000));

            log.info("Token refreshed successfully for user: {}", username);
            return response;

        } catch (Exception e) {
            log.error("Token refresh failed: {}", e.getMessage(), e);
            throw new RuntimeException("Token refresh failed: " + e.getMessage());
        }
    }

    @Override
    public void logout(String token) {
        try {
            if (!StringUtils.hasText(token)) {
                throw new RuntimeException("Token is required for logout");
            }

            String bearerToken = token;
            if (token.startsWith("Bearer ")) {
                bearerToken = token.substring(7);
            }

            if (!jwtGenerator.validateToken(bearerToken)) {
                throw new RuntimeException("Invalid token");
            }

            String username = jwtGenerator.getUsernameFromJWT(bearerToken);
            tokenBlacklistService.blacklistToken(bearerToken, username, "User logout");

            log.info("User logged out successfully: {}", username);

        } catch (Exception e) {
            log.error("Logout failed: {}", e.getMessage(), e);
            throw new RuntimeException("Logout failed: " + e.getMessage());
        }
    }
}
