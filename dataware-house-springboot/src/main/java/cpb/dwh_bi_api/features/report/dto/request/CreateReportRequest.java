package cpb.dwh_bi_api.features.report.dto.request;

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
public class CreateReportRequest {
	@NotBlank(message = "Name is required")
	private String name;

	private String code;

	@NotNull(message = "Status is required")
	private Boolean status;

	@NotNull(message = "MIS flag is required")
	private Boolean mis;

	private String category;

	private UUID unitId;

	private UUID departmentId;
}
