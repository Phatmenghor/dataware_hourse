package cpb.dwh_bi_api.shared.enums;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ACTIVE("Active"),
    END_WORK("End Work"),
    LOCKED("Locked");

    private final String description;

    AccountStatus(String description) {
        this.description = description;
    }
}
