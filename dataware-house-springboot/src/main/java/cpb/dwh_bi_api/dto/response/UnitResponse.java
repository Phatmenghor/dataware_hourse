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
public class UnitResponse {
	private UUID id;
	private String name;
	private Boolean status;
	private UUID departmentId;
	private String departmentName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
