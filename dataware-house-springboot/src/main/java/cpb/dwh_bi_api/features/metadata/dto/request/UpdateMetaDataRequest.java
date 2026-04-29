package cpb.dwh_bi_api.features.metadata.dto.request;

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
public class UpdateMetaDataRequest {
	@NotNull(message = "ID is required")
	private UUID id;

	private String type;
	private String label;
	private String value;
}
