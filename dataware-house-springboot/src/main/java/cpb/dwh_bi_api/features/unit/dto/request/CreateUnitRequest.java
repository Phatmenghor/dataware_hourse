package cpb.dwh_bi_api.features.unit.dto.request;

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
public class CreateUnitRequest {
	@NotBlank(message = "Name is required")
	private String name;

	@NotNull(message = "Department ID is required")
	private UUID departmentId;

	@NotNull(message = "Status is required")
	private Boolean status;
}
