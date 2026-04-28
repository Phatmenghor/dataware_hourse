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
@Table(name = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

	@Id
	@Column(columnDefinition = "UUID")
	private UUID id;

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
