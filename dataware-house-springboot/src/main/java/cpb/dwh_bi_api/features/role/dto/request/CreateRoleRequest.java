package cpb.dwh_bi_api.features.role.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRoleRequest {
	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Code is required")
	private String code;

	@NotNull(message = "Status is required")
	private Boolean status;
}
