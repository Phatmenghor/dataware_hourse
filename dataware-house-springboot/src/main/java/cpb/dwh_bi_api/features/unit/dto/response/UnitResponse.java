package cpb.dwh_bi_api.features.unit.dto.response;

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
	private UUID departmentId;
	private Boolean status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
