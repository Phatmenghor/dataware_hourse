package cpb.dwh_bi_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDepartmentRequest {
	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Short name is required")
	private String shortName;

	@NotBlank(message = "Code is required")
	private String code;

	@NotNull(message = "Status is required")
	private Boolean status;

	private String type;
}
