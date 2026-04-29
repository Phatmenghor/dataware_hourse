package cpb.dwh_bi_api.features.report.models;

import cpb.dwh_bi_api.shared.domain.BaseUUIDEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "report_columns")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true, exclude = {"reportRole"})
@ToString(exclude = {"reportRole"})
public class ReportColumn extends BaseUUIDEntity {

	@Column(nullable = false)
	private String display;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private Boolean status = false;

	@Column(nullable = false)
	private Integer ordering = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "report_role_id", nullable = false)
	private ReportRole reportRole;
}
