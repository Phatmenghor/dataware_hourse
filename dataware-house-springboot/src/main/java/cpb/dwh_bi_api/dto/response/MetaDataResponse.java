package cpb.dwh_bi_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaDataResponse {
	private UUID id;
	private String type;
	private String label;
	private String value;
}
