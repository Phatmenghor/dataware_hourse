package cpb.dwh_bi_api.features.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
	private UUID id;
	private String fullName;
	private String staffId;
	private String username;
	private Boolean status;
	private LocalDateTime passwordExpired;
	private LocalDateTime passwordResetedAt;
	private UUID departmentId;
	private UUID positionId;
	private UUID roleId;
	private String departmentName;
	private String positionName;
	private String roleName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
