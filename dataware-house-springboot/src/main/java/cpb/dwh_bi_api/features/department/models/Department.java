package cpb.dwh_bi_api.features.department.models;

import cpb.dwh_bi_api.features.report.models.Report;
import cpb.dwh_bi_api.features.unit.models.Unit;
import cpb.dwh_bi_api.features.user.models.User;
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
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true, exclude = {"users", "units", "reports"})
@ToString(exclude = {"users", "units", "reports"})
public class Department extends BaseUUIDEntity {

	@Column(nullable = false)
	private String name;

	@Column(name = "short_name", nullable = false)
	private String shortName;

	@Column(nullable = false, unique = true)
	private String code;

	@Column(nullable = false)
	private Boolean status = false;

	@Column(nullable = true)
	private String type;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<User> users = new HashSet<>();

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Unit> units = new HashSet<>();

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Report> reports = new HashSet<>();
}
