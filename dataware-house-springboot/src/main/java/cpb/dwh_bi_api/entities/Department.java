package cpb.dwh_bi_api.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

	@Id
	@Column(columnDefinition = "UUID")
	private UUID id;

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

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@PrePersist
	public void prePersist() {
		if (this.id == null) {
			this.id = UUID.randomUUID();
		}
	}
}
