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
@Table(name = "units")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Unit {

	@Id
	@Column(columnDefinition = "UUID")
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Boolean status = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	@OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
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
