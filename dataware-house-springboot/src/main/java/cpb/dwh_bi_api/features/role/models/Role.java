package cpb.dwh_bi_api.features.role.models;

import cpb.dwh_bi_api.features.report.models.ReportRole;
import cpb.dwh_bi_api.features.user.models.User;
import cpb.dwh_bi_api.features.widget.models.WidgetRole;
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
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true, exclude = {"users", "reportRoles", "widgetRoles"})
@ToString(exclude = {"users", "reportRoles", "widgetRoles"})
public class Role extends BaseUUIDEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String code;

	@Column(nullable = false)
	private Boolean status = false;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<User> users = new HashSet<>();

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ReportRole> reportRoles = new HashSet<>();

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<WidgetRole> widgetRoles = new HashSet<>();
}
