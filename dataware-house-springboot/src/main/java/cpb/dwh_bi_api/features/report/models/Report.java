package cpb.dwh_bi_api.features.report.models;

import cpb.dwh_bi_api.features.department.models.Department;
import cpb.dwh_bi_api.features.unit.models.Unit;
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
@Table(name = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true, exclude = {"unit", "department", "reportRoles", "params"})
@ToString(exclude = {"unit", "department", "reportRoles", "params"})
public class Report extends BaseUUIDEntity {

	@Column(nullable = false)
	private String name;

	@Column
	private String code;

	@Column(nullable = false)
	private Boolean status = false;

	@Column(nullable = false)
	private Boolean mis = false;

	@Column
	private String category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	private Unit unit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;

	@OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ReportRole> reportRoles = new HashSet<>();

	@OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ReportParam> params = new HashSet<>();
}
