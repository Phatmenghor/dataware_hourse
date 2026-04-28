package cpb.dwh_bi_api.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "widget_params")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WidgetParam {

	@Id
	@Column(columnDefinition = "UUID")
	private UUID id;

	@Column(nullable = false)
	private String display;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private Integer ordering = 0;

	@Column(nullable = false)
	private Boolean status = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "widget_id", nullable = false)
	private Widget widget;

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
