package cpb.dwh_bi_api.features.unit.models;

import cpb.dwh_bi_api.features.department.models.Department;
import cpb.dwh_bi_api.features.report.models.Report;
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
@Table(name = "units")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true, exclude = {"department", "reports"})
@ToString(exclude = {"department", "reports"})
public class Unit extends BaseUUIDEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Boolean status = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	@OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Report> reports = new HashSet<>();
}
