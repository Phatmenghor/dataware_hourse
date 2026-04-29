package cpb.dwh_bi_api.features.user.dto.request;

import jakarta.validation.constraints.NotBlank;
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
public class CreateUserRequest {
	@NotBlank(message = "Full name is required")
	private String fullName;

	@NotBlank(message = "Staff ID is required")
	private String staffId;

	@NotBlank(message = "Username is required")
	private String username;

	@NotBlank(message = "Password is required")
	private String password;

	@NotNull(message = "Department is required")
	private UUID departmentId;

	@NotNull(message = "Position is required")
	private UUID positionId;

	@NotNull(message = "Role is required")
	private UUID roleId;
}
