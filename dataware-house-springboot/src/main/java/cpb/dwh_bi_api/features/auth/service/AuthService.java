package cpb.dwh_bi_api.features.auth.service;

import cpb.dwh_bi_api.features.auth.dto.LoginRequest;
import cpb.dwh_bi_api.features.auth.dto.RefreshTokenRequest;
import cpb.dwh_bi_api.features.auth.dto.TokenResponse;

public interface AuthService {

    TokenResponse login(LoginRequest loginRequest);

    TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    void logout(String token);
}
