package cpb.dwh_bi_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUnitRequest {
	@NotBlank(message = "Name is required")
	private String name;

	@NotNull(message = "Department is required")
	private UUID departmentId;

	@NotNull(message = "Status is required")
	private Boolean status;
}
