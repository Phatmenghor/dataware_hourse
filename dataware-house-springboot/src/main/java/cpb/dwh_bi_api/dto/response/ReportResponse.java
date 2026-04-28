package cpb.dwh_bi_api.dto.response;

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
public class ReportResponse {
	private UUID id;
	private String name;
	private String code;
	private Boolean status;
	private Boolean mis;
	private String category;
	private UUID unitId;
	private UUID departmentId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
