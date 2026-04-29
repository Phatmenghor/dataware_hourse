package cpb.dwh_bi_api.features.widget.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WidgetResponse {
	private UUID id;
	private String name;
	private String type;
	private String code;
	private String position;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
