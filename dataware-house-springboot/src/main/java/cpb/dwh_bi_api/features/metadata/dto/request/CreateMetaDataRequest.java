package cpb.dwh_bi_api.features.metadata.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMetaDataRequest {
	@NotBlank(message = "Type is required")
	private String type;

	@NotBlank(message = "Label is required")
	private String label;

	@NotBlank(message = "Value is required")
	private String value;
}
