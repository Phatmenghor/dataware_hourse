package cpb.dwh_bi_api.features.widget.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWidgetRequest {
	@NotBlank(message = "Name is required")
	private String name;

	private String type;
	private String code;
	private String position;
}
