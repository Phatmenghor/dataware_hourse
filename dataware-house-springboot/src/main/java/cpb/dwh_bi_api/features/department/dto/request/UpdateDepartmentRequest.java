package cpb.dwh_bi_api.features.department.dto.request;

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
public class UpdateDepartmentRequest {
	@NotNull(message = "ID is required")
	private UUID id;

	private String name;
	private String shortName;
	private String code;
	private Boolean status;
	private String type;
}
