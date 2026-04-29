package cpb.dwh_bi_api.features.report.models;

import cpb.dwh_bi_api.features.role.models.Role;
import cpb.dwh_bi_api.shared.domain.BaseUUIDEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "report_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true, exclude = {"report", "role", "reportColumns"})
@ToString(exclude = {"report", "role", "reportColumns"})
public class ReportRole extends BaseUUIDEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "report_id", nullable = false)
	private Report report;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@OneToMany(mappedBy = "reportRole", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ReportColumn> reportColumns = new HashSet<>();
}
