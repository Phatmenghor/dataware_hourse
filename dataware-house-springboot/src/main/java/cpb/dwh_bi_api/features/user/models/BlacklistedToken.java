package cpb.dwh_bi_api.features.user.models;

import cpb.dwh_bi_api.shared.domain.BaseUUIDEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "blacklisted_tokens", indexes = {
        @Index(name = "idx_token", columnList = "token"),
        @Index(name = "idx_user_identifier", columnList = "user_identifier"),
        @Index(name = "idx_expiry_date", columnList = "expiry_date")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class BlacklistedToken extends BaseUUIDEntity {

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String token;

    @Column(nullable = false)
    private String userIdentifier;

    @Column(nullable = false)
    private LocalDateTime blacklistedAt;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Column
    private String reason;

    public BlacklistedToken(String token, String userIdentifier, LocalDateTime expiryDate, String reason) {
        this.token = token;
        this.userIdentifier = userIdentifier;
        this.expiryDate = expiryDate;
        this.reason = reason;
        this.blacklistedAt = LocalDateTime.now();
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }
}
