package cpb.dwh_bi_api.features.user.models;

import cpb.dwh_bi_api.features.department.models.Department;
import cpb.dwh_bi_api.features.position.models.Position;
import cpb.dwh_bi_api.features.role.models.Role;
import cpb.dwh_bi_api.features.widget.models.WidgetFavorite;
import cpb.dwh_bi_api.shared.domain.BaseUUIDEntity;
import cpb.dwh_bi_api.shared.enums.AccountStatus;
import cpb.dwh_bi_api.shared.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true, exclude = {"department", "position", "role", "widgetFavorites"})
@ToString(exclude = {"department", "position", "role", "widgetFavorites"})
public class User extends BaseUUIDEntity {

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "staff_id", unique = true)
	private String staffId;

	@Column(unique = true)
	private String username;

	@Column
	private String password;

	@Column(name = "password_expired")
	private LocalDateTime passwordExpired;

	@Column(name = "password_reseted_at")
	private LocalDateTime passwordResetedAt;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus = AccountStatus.ACTIVE;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private Position position;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<WidgetFavorite> widgetFavorites = new HashSet<>();
}
