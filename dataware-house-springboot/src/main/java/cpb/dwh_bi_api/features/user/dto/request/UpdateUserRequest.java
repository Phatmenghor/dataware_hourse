package cpb.dwh_bi_api.features.user.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
	@NotNull(message = "ID is required")
	private UUID id;

	private String fullName;
	private String staffId;
	private String username;
	private String password;
	private Boolean status;
	private UUID departmentId;
	private UUID positionId;
	private UUID roleId;
}
