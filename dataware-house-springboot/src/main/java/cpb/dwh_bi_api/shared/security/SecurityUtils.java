package cpb.dwh_bi_api.shared.security;

import cpb.dwh_bi_api.features.user.models.User;
import cpb.dwh_bi_api.features.user.repository.UserRepository;
import cpb.dwh_bi_api.shared.enums.AccountStatus;
import cpb.dwh_bi_api.shared.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class SecurityUtils {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String getCurrentUserIdentifier() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return authentication.getName();
    }

    public void validateAccountStatus(User user) {
        if (user.getStatus() == Status.INACTIVE) {
            throw new RuntimeException("Account has been disabled by platform");
        }

        if (user.getAccountStatus() == AccountStatus.LOCKED) {
            throw new RuntimeException("Account is locked");
        }

        if (user.getAccountStatus() == AccountStatus.END_WORK) {
            throw new RuntimeException("Account has been ended");
        }
    }

    public boolean isCurrentUser(String username) {
        String currentUsername = getCurrentUserIdentifier();
        return currentUsername != null && currentUsername.equals(username);
    }

    public Optional<User> getCurrentUserOptional() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null ||
                    !authentication.isAuthenticated() ||
                    "anonymousUser".equals(authentication.getPrincipal())) {
                log.debug("No authenticated user - public access");
                return Optional.empty();
            }

            String username = authentication.getName();
            Optional<User> userOpt = userRepository.findByUsername(username);

            if (userOpt.isEmpty()) {
                log.warn("Authenticated user not found in database: {}", username);
                return Optional.empty();
            }

            User user = userOpt.get();

            try {
                validateAccountStatus(user);
            } catch (Exception e) {
                log.warn("User account validation failed: {} - {}", username, e.getMessage());
                return Optional.empty();
            }

            return Optional.of(user);

        } catch (Exception e) {
            log.debug("Error getting current user (public access mode): {}", e.getMessage());
            return Optional.empty();
        }
    }

    public UUID getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public Optional<UUID> getCurrentUserIdOptional() {
        return getCurrentUserOptional().map(User::getId);
    }
}
