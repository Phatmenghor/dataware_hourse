package cpb.dwh_bi_api.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "widget_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WidgetRole {

	@Id
	@Column(columnDefinition = "UUID")
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "widget_id", nullable = false)
	private Widget widget;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

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
